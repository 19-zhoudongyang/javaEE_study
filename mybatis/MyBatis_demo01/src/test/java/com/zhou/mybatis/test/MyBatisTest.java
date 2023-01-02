package com.zhou.mybatis.test;

import com.zhou.mybatis.mappers.UserMapper;
import com.zhou.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    //测试insertUser
    @Test
    public void testInsertUser() throws IOException {
        //加载核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SQLSessionFactoryBuilder
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        //获取SQLSessionFactory
        SqlSessionFactory sessionFactory = factoryBuilder.build(is);
        //获取SQLSession(代表javb程序和数据库之间的会话)
        /*
            可用此构造方法传入boolean参数设置是否自动提交
            空参构造器默认不自动提交
            true则为自动提交
         */
        SqlSession sqlSession = sessionFactory.openSession(true);
        //获取mapper接口对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        int result = mapper.insertUser();
        //提交事务
        //sqlSession.commit();
        //打印结果
        System.out.println("结果：" + result);
    }

    //测试updateUser
    @Test
    public void testUpdateUser() throws IOException {
        //加载核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SQLSessionFactoryBuilder
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        //获取SQLSessionFactory
        SqlSessionFactory sessionFactory = factoryBuilder.build(is);
        //获取SQLSession(代表javb程序和数据库之间的会话)
        /*
            可用此构造方法传入boolean参数设置是否自动提交
            空参构造器默认不自动提交
            true则为自动提交
         */
        SqlSession sqlSession = sessionFactory.openSession(true);
        //获取mapper接口对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        mapper.updateUser();
        //提交事务
        //sqlSession.commit();
    }

    //测试deleteUser
    @Test
    public void testDeleteUser() throws IOException {
        //加载核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SQLSessionFactoryBuilder
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        //获取SQLSessionFactory
        SqlSessionFactory sessionFactory = factoryBuilder.build(is);
        //获取SQLSession(代表javb程序和数据库之间的会话)
        /*
            可用此构造方法传入boolean参数设置是否自动提交
            空参构造器默认不自动提交
            true则为自动提交
         */
        SqlSession sqlSession = sessionFactory.openSession(true);
        //获取mapper接口对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        mapper.deleteUser();
        //提交事务
        //sqlSession.commit();
    }

    //测试selectUserById
    @Test
    public void testSelectUserById() throws IOException {
        //加载核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SQLSessionFactoryBuilder
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        //获取SQLSessionFactory
        SqlSessionFactory sessionFactory = factoryBuilder.build(is);
        //获取SQLSession(代表javb程序和数据库之间的会话)
        /*
            可用此构造方法传入boolean参数设置是否自动提交
            空参构造器默认不自动提交
            true则为自动提交
         */
        SqlSession sqlSession = sessionFactory.openSession(true);
        //获取mapper接口对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = mapper.selectUserById();
        //提交事务
        //sqlSession.commit();
        System.out.println(user);
    }

    //测试getAllUser
    @Test
    public void testGetAllUser() throws IOException {
        //加载核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SQLSessionFactoryBuilder
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        //获取SQLSessionFactory
        SqlSessionFactory sessionFactory = factoryBuilder.build(is);
        //获取SQLSession(代表javb程序和数据库之间的会话)
        /*
            可用此构造方法传入boolean参数设置是否自动提交
            空参构造器默认不自动提交
            true则为自动提交
         */
        SqlSession sqlSession = sessionFactory.openSession(true);
        //获取mapper接口对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        List<User> allUser = mapper.getAllUser();
        //提交事务
        //sqlSession.commit();
        for (User user : allUser) {
            System.out.println(user);
        }
    }

}
