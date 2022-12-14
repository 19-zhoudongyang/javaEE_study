package com.zhou.mybatis.mappers;

import com.zhou.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectMapper {
    /*
    根据id查询用户信息
     */
    User getUserById(@Param("id") Integer id);

    /*
    查询所有用户信息
     */
    List<User> getAllUser();

    /*
    查询数据的数量
     */
    Integer getCount();

    /*
    根据id查询用户信息为一个map集合
     */
    Map<String, Object> getUserByIdToMap(@Param("id") Integer id);

    /*
    查询所有用户信息为一个map集合
     */
    @MapKey("id")
    Map<String, Object> getAllUserToMap();

}
