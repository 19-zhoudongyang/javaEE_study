package com.zhou.mybatis.test;

import com.zhou.mybatis.mappers.DeptMapper;
import com.zhou.mybatis.mappers.EmpMapper;
import com.zhou.mybatis.pojo.Dept;
import com.zhou.mybatis.pojo.Emp;
import com.zhou.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ResultMapTest {

    @Test
    public void testGetAllEmp() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> allEmp = mapper.getAllEmp();
        allEmp.forEach(emp -> System.out.println(emp));
    }

    @Test
    public void testGetEmpAndDept() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp empAndDept = mapper.getEmpAndDept(1);
        System.out.println(empAndDept);
    }

    @Test
    public void testGetEmpAndDeptByStep() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp empAndDeptByStep = mapper.getEmpAndDeptByStepOne(1);
        System.out.println(empAndDeptByStep);
    }

    @Test
    public void testGetDeptAndEmpResultMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(true);
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept deptAndEmp = mapper.getDeptAndEmp(2);
        System.out.println(deptAndEmp);
    }


}
