package com.zhou.mybatis.test;

import com.zhou.mybatis.mappers.ParameterMapper;
import com.zhou.mybatis.pojo.User;
import com.zhou.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;

public class MyBatisTest {

    @Test
    public void testSelectUserById() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.selectUserById(4);
        System.out.println(user);
    }

    @Test
    public void testSelectUserByIdAndUsername() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.selectUserByIdAndUsername(4, "admin");
        System.out.println(user);
    }

    @Test
    public void testCheckLoginByMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 4);
        map.put("username", "admin");
        User user = mapper.checkLoginByMap(map);
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = new User(null, "李四", "123456", 23, "男", "123456789@qq.com");
        int result = mapper.insertUser(user);
        System.out.println(result);
    }

    @Test
    public void testCheckLoginByParam() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLoginByParam("李四", "123456");
        System.out.println(user);
    }
}
