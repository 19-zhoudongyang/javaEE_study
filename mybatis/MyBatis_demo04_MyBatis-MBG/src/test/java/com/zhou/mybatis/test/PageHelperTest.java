package com.zhou.mybatis.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.mybatis.mappers.EmpMapper;
import com.zhou.mybatis.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PageHelperTest {

    /**
     * limit index,pageSize
     * index:当前页的起始索引
     * pageSize:每页显示的条数
     * pageNum:当前页的页码
     * index=(pageNum-1)*pageSize
     */
    @Test
    public void testPageHelper() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //开启分页功能
            PageHelper.startPage(2, 4);

            List<Emp> emps = mapper.selectByExample(null);
            //navigatePages:显示导航分页的个数
            PageInfo<Emp> pageInfo = new PageInfo<>(emps, 5);
            System.out.println(pageInfo);

            //emps.forEach(emp -> System.out.println(emp));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
