package com.zhou.jedis;

import redis.clients.jedis.Jedis;

public class JedisDemo01 {
    public static void main(String[] args) {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.201.100", 6379);

        //测试
        String ping = jedis.ping();
        System.out.println(ping);
    }


}
