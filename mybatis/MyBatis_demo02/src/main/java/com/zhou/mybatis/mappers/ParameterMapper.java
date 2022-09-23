package com.zhou.mybatis.mappers;

import com.zhou.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ParameterMapper {
    /*
    根据id查询用户信息
     */
    User selectUserById(int id);

    /*
    根据id和username查询用户信息
     */
    User selectUserByIdAndUsername(int id,String username);

    /*
    验证登录
     */
    User checkLoginByMap(Map<String,Object> map);

    /*
    添加用户信息
     */
    int insertUser(User user);

    /*
    验证登录(使用注解@Param)
     */
    User checkLoginByParam(@Param("username") String username, @Param("password") String password);
}
