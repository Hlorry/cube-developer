package cn.getcube.develop.anaotation;

import java.lang.annotation.*;

/**
 * Created by Rainbow
 *
 * @author SubDong
 * @version V2.0
 *          Copyright (c)2017 shixin-版权所有
 * @since 2017/5/5
 */
@Documented//文档
@Retention(RetentionPolicy.RUNTIME)//在运行时可以获取
@Target({ElementType.TYPE, ElementType.METHOD})//作用到类，方法，接口上等
public @interface TokenVerify {
    //枚举类型
    enum VerifyEnum {
        NORMAL, MINE, OTHER
    }

    //实际的值
    VerifyEnum verifyType() default VerifyEnum.NORMAL;
}
