package com.huiluczp.service;

import com.huiluczp.bean.User;
import com.huiluczp.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 验证用户
    public boolean checkUser(String userName, String password){
        User user = userMapper.getUserByName(userName);
        if(user!=null){
            System.out.println(user.getPassword());
            System.out.println(password);
            return user.getPassword().equals(password);
        }else {
            return false;
        }
    }

    // 增加用户
    public boolean addUser(String userName, String password, String role){
        return userMapper.addUser(userName, password, role)==1;
    }

    // 获取用户信息
    public User getUser(String userName){
        return userMapper.getUserByName(userName);
    }
}
