package cn.getcube.develop.commons;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by SubDong on 2016/3/8.
 */
public class CubeInterceptor implements HandlerInterceptor {

    @Resource
    JedisCluster jc;

    private List<String> allowUrls;


    public List<String> getAllowUrls() {
        return allowUrls;
    }

    public void setAllowUrls(List<String> allowUrls) {
        this.allowUrls = allowUrls;
    }

    private List<String> notNeedTokenUrls;

    public void setNotNeedTokenUrls(List<String> notNeedTokenUrls) {
        this.notNeedTokenUrls = notNeedTokenUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestUrl = request.getRequestURI();
        String token = request.getParameter("token");
        if (token == null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("cube_develops_token")) {
                    token = cookie.getValue();
                }
            }
        }
        //对所有的请求,进行拦截
        if (requestUrl != null) {
            /**
             * 不进行拦截url
             */
            if(requestUrl.contains("/css/") || requestUrl.contains("/img/") || requestUrl.contains("/js/") || requestUrl.contains("/cubeImage/")){
                return true;
            }
            for (String url : allowUrls) {
                if (url.indexOf("*") != -1) {
                    String newUrl = url.substring(0, url.indexOf("/*"));
                    if (requestUrl.indexOf(newUrl) != -1) {
                        return judgeToken(requestUrl, token, request, response);
                    }
                } else if (requestUrl.equals(url)) {
                    return judgeToken(requestUrl, token, request, response);
                }
            }
        } else {
            return false;
        }
        return false;
    }

    private boolean judgeToken(String requestUrl, String token, HttpServletRequest request, HttpServletResponse response) {
        boolean flag = true;
        for (String url : notNeedTokenUrls) {
            if (url.indexOf("*") != -1) {
                String newUrl = url.substring(0, url.indexOf("/*"));
                if (requestUrl.indexOf(newUrl) != -1) {
                    flag = false;
                }
            } else {
                if (requestUrl.indexOf(url) != -1) {
                    flag = false;
                }
            }
        }
        if (flag) {
            if (token == null || !jc.exists(token)) {
                if (request.getCookies() != null) {
                    for (Cookie cookie : request.getCookies()) {
                        Cookie cookie1 = new Cookie(cookie.getName(), null);
                        cookie1.setPath("/");
                        cookie1.setMaxAge(0);
                        response.addCookie(cookie1);
                    }
                }

                try {
                    response.sendRedirect("/route/login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
