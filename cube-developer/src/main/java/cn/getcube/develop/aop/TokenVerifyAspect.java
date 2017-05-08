package cn.getcube.develop.aop;

import cn.getcube.develop.StateCode;
import cn.getcube.develop.anaotation.TokenVerify;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.utils.BaseResult;
import cn.getcube.develop.utils.UserSessionTokenVerify;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Rainbow
 *
 * @author SubDong
 * @version V2.0
 *          Copyright (c)2017 shixin-版权所有
 * @since 2017/5/5
 */
@Aspect
@Component
public class TokenVerifyAspect {

    private Logger logger = Logger.getLogger(TokenVerifyAspect.class);

    @Pointcut("execution(public * cn.getcube.develop.controller.*.*(..))")
    public void verifyAspect() {
    }

    @Before("verifyAspect()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.trace("URL : " + request.getRequestURL().toString());
        logger.trace("HTTP_METHOD : " + request.getMethod());
        logger.trace("IP : " + request.getRemoteAddr());
        logger.trace("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.trace("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("verifyAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取当前执行方法
        Method currentMethod = getCurrentMethod(pjp);
        // 构建模拟参数
        Object[] copyArgs = Arrays.copyOf(pjp.getArgs(), pjp.getArgs().length);
        // 不使用权限验证的方法直接跳过
        if (Objects.nonNull(currentMethod.getAnnotation(TokenVerify.class))) {
            // 获取token
            String token = getCurrentToken(pjp.getArgs());
            if (StringUtils.isEmpty(token)) {
                //必须 返回DataResult 不能是BaseResult
                return BaseResult.build(StateCode.AUTH_ERROR_10016, "The required 'token' is missing from the request...");
            }
            Object result = handleToken(token, currentMethod);
            if (Objects.nonNull(result)) {
                if (result instanceof UserEntity) {
                    // 获取UserEntity参数的位置
                    int i = 0;
                    for (Object o : pjp.getArgs()) {
                        if (o instanceof UserEntity) {
                            copyArgs[i] = result;
                        }
                        i++;
                    }
                } else {
                    return result;
                }
            }
        }
        return pjp.proceed(copyArgs);
    }

    private String getCurrentToken(Object[] args) {
        logger.trace("args : " + Arrays.toString(args));
        // 判断是否含有UserEntity参数
        for (Object o : args) {
            if (o != null && o.toString().contains("tn_") && o.toString().length() == 35) {
                return o.toString();
            }
        }
        // 如果没有UserEntity参数，通过第一个参数获取token
        try {
            return String.valueOf(args[0]);
        } catch (Exception e) {
            logger.trace(e.getMessage());
        }
        return null;
    }

    private Object handleToken(String token, Method currentMethod) {
        TokenVerify.VerifyEnum verifyType = currentMethod.getAnnotation(TokenVerify.class).verifyType();
        if (TokenVerify.VerifyEnum.NORMAL.equals(verifyType)) {
            UserEntity _session = UserSessionTokenVerify.get(token);
            if (Objects.nonNull(_session)) {
                logger.trace("get user session end, token->" + token + ", id->" + _session.getId() + ", plat->" + _session.getName());
                return _session;
            }
        }
        //必须 返回DataResult 不能是BaseResult
        return BaseResult.build(StateCode.TokenFailed, "Token failure");
    }

    private Method getCurrentMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Signature sig = pjp.getSignature();
        MethodSignature methodSignature;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) sig;
        Object target = pjp.getTarget();
        return target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    @AfterReturning(returning = "ret", pointcut = "verifyAspect()")
    public void doAfterReturning(Object ret) throws Throwable {
        if (null != ret) {
            // 处理完请求，返回内容
            logger.trace("RESPONSE : " + ret.toString());
        }
    }
}
