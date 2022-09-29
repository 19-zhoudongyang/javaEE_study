package com.zhou.mybatisplus;

import com.zhou.mybatisplus.mapper.UserMapper;
import com.zhou.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<User> users = userMapper.selectList(null);
        users.forEach(user -> System.out.println(user));
    }

//    @Test
//    public void testinsert(){
//        User user = new User(null,"张三", 23,"13123@qq.com");
//        int result = userMapper.insert(user);
//        System.out.println("受影响的行数："+result);
//        System.out.println("id:"+user.getId());
//    }

    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(1575383239586136066L);
        System.out.println("受影响的行数："+result);

    }
}
