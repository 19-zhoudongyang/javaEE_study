package com.zhou.mybatis.test;

import com.zhou.mybatis.mappers.SQLMapper;
import com.zhou.mybatis.pojo.User;
import com.zhou.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class SQLMapperTest {
    @Test
    public void testGetUserByLike() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        List<User> user = mapper.getUserByLike("ad");
        System.out.println(user);
    }

    @Test
    public void TestDeleteMore() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        int i = mapper.deleteMore("4,5,6");
        System.out.println(i);
    }

    @Test
    public void TestGetUserByTableName() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        List<User> users = mapper.getUserByTableName("t_user");
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void TestInsertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        User user = new User(null, "王五", "123", 23, "男", "1231231@qq.com");
        mapper.insertUser(user);
        System.out.println(user);

    }
}
