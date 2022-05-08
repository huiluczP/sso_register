package com.huiluczp.mapper;

import com.huiluczp.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select id, username as userName, password, role " +
            "from user where username = #{userName}")
    public User getUserByName(String userName);

    @Insert("insert into user(username, password, role)" +
            "values(#{userName}, #{password}, #{role})")
    public int addUser(String userName, String password, String role);
}
