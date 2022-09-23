package com.zhou.mybatis.mappers;

import com.zhou.mybatis.pojo.User;

import java.util.List;

public interface UserMapper {
    /*
    添加用户信息
     */
    int insertUser();

    /*
    修改用户信息
     */
    void updateUser();

    /*
    删除用户信息
     */
    void deleteUser();

    /*
    根据用户id查询数据
     */
    User selectUserById();

    /*
    查询所有用户信息
     */
    List<User> getAllUser();

}
