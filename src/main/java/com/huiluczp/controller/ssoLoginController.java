package com.huiluczp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huiluczp.bean.User;
import com.huiluczp.bean.response.CommonResponse;
import com.huiluczp.service.RedisService;
import com.huiluczp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ssoLoginController {
    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String test(String token){
        return token+ ":" + redisService.isTokenExist(token);
    }

    @RequestMapping("/login")
    @ResponseBody
    // 验证登录，数据库对接
    // 成功后redis增加token缓存，token就简单用username即可
    public CommonResponse login(HttpServletResponse response, String userName, String password, String url) throws JsonProcessingException {
        if(userService.checkUser(userName, password)){
            // token生成并redirect到原始url
            String time = redisService.addToken(userName);
            System.out.println("redis已成功添加token 时效:" + time);
            Cookie cookie = new Cookie("accessToken", userName);
            //设置访问路径
            cookie.setPath("/");
            // 设置1小时先
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);
            //重定向到原先访问的页面
            // response.sendRedirect(url);
            return new CommonResponse(true, url);
        }else{
            return new CommonResponse(false, "用户名或密码错误");
        }
    }

    @RequestMapping("/validate")
    @ResponseBody
    // 验证token的有效性
    public CommonResponse validate(String token) throws JsonProcessingException {
        if(redisService.isTokenExist(token)){
            // 解析token，获取对应可暴露信息
            String userJson = redisService.getValue(token);
            return new CommonResponse(true, userJson);
        }else {
            System.out.println("get token:" + token + " 失败");
            return new CommonResponse(false, "false");
        }
    }

    @RequestMapping("/user/login")
    // 统一的登录页面
    public String loginPage(){
        return "/login.html";
    }

    @RequestMapping("/user/register")
    // 注册页面
    public String registerPage(){
        return "/register.html";
    }

    @RequestMapping("/user/success")
    // 注册页面
    public String successPage(){
        return "/success.html";
    }

    @RequestMapping("/logout")
    @ResponseBody
    // 验证token的有效性
    public CommonResponse logout(String token) throws JsonProcessingException {
        redisService.deleteToken(token);
        System.out.println("token: " + token + " 删除成功");
        return new CommonResponse(true, "登出成功");
    }
}
