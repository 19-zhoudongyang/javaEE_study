package com.zhou.mybatis.test;

import com.zhou.mybatis.mappers.DynamicSQLMapper;
import com.zhou.mybatis.pojo.Emp;
import com.zhou.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DynamicSQLMapperTest {

    @Test
    public void testGetEmpByCondition(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> empByCondition = mapper.getEmpByCondition(new Emp(null, "张三", 12, "男", "213213@qq.com"));
        System.out.println(empByCondition);
    }
    @Test
    public void testGetEmpByChoose(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> empByChoose = mapper.getEmpByChoose(new Emp(null, "", null, "", ""));
        empByChoose.forEach(emp -> System.out.println(emp));
    }

    @Test
    public void testDeleteMoreByArray(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int i = mapper.deleteMoreByArray(new Integer[]{5,6,7,8,9,10});
    }

    @Test
    public void testInsertMoreByList(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp1 = new Emp(null, "a1", 23, "男", "123124@qq.com");
        Emp emp2 = new Emp(null,"a2",23,"男","123124@qq.com");
        Emp emp3 = new Emp(null,"a3",23,"男","123124@qq.com");
        List<Emp> emps = Arrays.asList(emp1, emp2, emp3);
        int i = mapper.insertMoreByList(emps);
    }
}
