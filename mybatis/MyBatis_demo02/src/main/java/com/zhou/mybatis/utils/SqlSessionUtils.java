package com.zhou.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtils {
    //自动提交事务
    public static SqlSession getSqlSession(boolean autoCommit) {
        InputStream is = null;
        SqlSessionFactoryBuilder factoryBuilder = null;
        SqlSessionFactory sessionFactory = null;
        try {
            //加载核心配置文件
            is = Resources.getResourceAsStream("mybatis-config.xml");
            //获取SQLSessionFactoryBuilder
            factoryBuilder = new SqlSessionFactoryBuilder();
            //获取SQLSessionFactory
            sessionFactory = factoryBuilder.build(is);
            //获取SQLSession(代表javb程序和数据库之间的会话)
            /*
            可用此构造方法传入boolean参数设置是否自动提交
            空参构造器默认不自动提交
            true则为自动提交
            */

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory.openSession(autoCommit);
    }

    //默认构造器为不自动提交事务
    public static SqlSession getSqlSession() {
        InputStream is = null;
        SqlSessionFactoryBuilder factoryBuilder = null;
        SqlSessionFactory sessionFactory = null;
        try {
            //加载核心配置文件
            is = Resources.getResourceAsStream("mybatis-config.xml");
            //获取SQLSessionFactoryBuilder
            factoryBuilder = new SqlSessionFactoryBuilder();
            //获取SQLSessionFactory
            sessionFactory = factoryBuilder.build(is);
            //获取SQLSession(代表javb程序和数据库之间的会话)
            /*
            可用此构造方法传入boolean参数设置是否自动提交
            空参构造器默认不自动提交
            true则为自动提交
            */

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory.openSession();
    }
}
