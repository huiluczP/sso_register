package com.huiluczp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huiluczp.bean.User;
import com.huiluczp.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

// 将token操作类
@Service
public class RedisService {
    @Value("${token.over-time}")
    private long tokenOverTime;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserService userService;

    // 增加token缓存，当存在，更新过期时间
    public String addToken(String token) throws JsonProcessingException {
        if(redisUtil.hasKey(token)){
            // 更新过期时间
            redisUtil.expire(token, tokenOverTime, TimeUnit.SECONDS);
        }else{
            User user = userService.getUser(token);
            ObjectMapper mapper = new ObjectMapper();
            String userJson = mapper.writeValueAsString(user);
            redisUtil.add(token, userJson, tokenOverTime, TimeUnit.SECONDS);
            System.out.println("add token:" + token);
        }
        return String.valueOf(redisUtil.getExpire(token, TimeUnit.SECONDS));
    }

    // 查找token是否存在
    public boolean isTokenExist(String token){
        return redisUtil.get(token) != null;
    }

    // 删除token
    public void deleteToken(String token){
        System.out.println("token: " + token + " 正在删除");
        if(isTokenExist(token))
            redisUtil.delete(token);
    }

    public String getValue(String token){
        return redisUtil.get(token);
    }
}
