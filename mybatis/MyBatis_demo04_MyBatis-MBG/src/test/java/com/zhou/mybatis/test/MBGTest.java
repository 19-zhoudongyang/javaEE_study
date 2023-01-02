package com.zhou.mybatis.test;

import com.zhou.mybatis.mappers.EmpMapper;
import com.zhou.mybatis.pojo.Emp;
import com.zhou.mybatis.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MBGTest {

    @Test
    public void testMBG() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

            //查询所有数据
            /*List<Emp> emps = mapper.selectByExample(null);
            emps.forEach(emp -> System.out.println(emp));*/

            //根据条件查询
            /*EmpExample example = new EmpExample();
            example.createCriteria().andEmpNameEqualTo("张三");
            List<Emp> emps = mapper.selectByExample(example);
            emps.forEach(emp -> System.out.println(emp));*/

            //选择性修改
            mapper.updateByPrimaryKeySelective(new Emp(1, "admin", 22, null, "19099@qq.com", 3));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
