package com.huiluczp.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class CookieInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies=request.getCookies();
        boolean isOk = false;
        if(cookies==null){
            System.out.println("未得到accessToken cookie信息");
            return true;
        }
        for(Cookie cookie:cookies) {
            if (cookie.getName().equals("accessToken")) {
                System.out.println("获得携带access token的cookie: " + cookie.getValue());
                isOk = true;
            }
        }
        if(!isOk)
            System.out.println("未得到accessToken cookie信息");
        return true;
    }
}
