[toc]

# 一、NoSQL数据库简介
>![1](pic/02.PNG)
>![1](pic/03.PNG)
>![1](pic/04.PNG)
>![1](pic/01.PNG)
>![1](pic/05.PNG)
>![1](pic/06.PNG)
>![1](pic/07.PNG)
>![1](pic/08.PNG)
>![1](pic/09.PNG)
>![1](pic/10.PNG)
# 二、Redis6的概述/安装/启动/相关知识
## 概述
>>![1](pic/11.PNG)
>>![1](pic/12.PNG)
>>![1](pic/13.PNG)
>>![1](pic/14.PNG)
## 安装
>>![1](pic/15.PNG)
>>![1](pic/16.PNG)
>>![1](pic/17.PNG)
>>![1](pic/18.PNG)
>>![1](pic/19.PNG)
>>![1](pic/20.PNG)
>>![1](pic/21.PNG)
>>![1](pic/22.PNG)
## 启动
### 前台启动(不推荐)
>>>![1](pic/23.PNG)
### 后台启动(推荐)
>>>![1](pic/24.PNG)
>>> 注意修改的是备份出来的文件
>>>![1](pic/25.PNG)
>>>![1](pic/26.PNG)
## 相关知识
>>![1](pic/27.PNG)
>>![1](pic/28.PNG)
# 三、常用五大数据类型
>![1](pic/29.PNG)
## Redis 键(key)
>>![1](pic/30.PNG)
## Redis 字符串(String)
>>![1](pic/31.PNG)
>>![1](pic/32.PNG)
>>![1](pic/33.PNG)
>>![1](pic/34.PNG)
>>![1](pic/35.PNG)
>>![1](pic/36.PNG)
## Redis 列表(List)
>>![1](pic/37.PNG)
>>![1](pic/38.PNG)
>>![1](pic/39.PNG)
>>![1](pic/40.PNG)
## Redis 集合(Set)
>>![1](pic/41.PNG)
>>![1](pic/42.PNG)
## Redis 哈希(Hash)
>>![1](pic/43.PNG)
>>![1](pic/44.PNG)
>>![1](pic/45.PNG)
## Redis有序集合Zset(sorted set)
>>![1](pic/46.PNG)
>>![1](pic/47.PNG)
>>![1](pic/48.PNG)
>>![1](pic/49.PNG)
# 四、Redis6配置文件详解
>![1](pic/50.PNG)
## ###Units单位###
>>![1](pic/51.PNG)
## ###INCLUDES包含###
>>![1](pic/52.PNG)
## ###网络相关配置 ###
### bind
>>>![1](pic/53.PNG)
>>>![1](pic/54.PNG)
### protected-mode
>>>![1](pic/55.PNG)
>>>![1](pic/56.PNG)
### Port
>>>![1](pic/57.PNG)
### tcp-backlog
>>>![1](pic/58.PNG)
### timeout
>>>![1](pic/59.PNG)
>>>![1](pic/60.PNG)
### tcp-keepalive
>>>![1](pic/61.PNG)
## ###GENERAL通用###
### daemonize
>>>![1](pic/62.PNG)
### pidfile
>>>![1](pic/63.PNG)
>>>![1](pic/64.PNG)
### loglevel
>>>![1](pic/65.PNG)
### logfile
>>>![1](pic/66.PNG)
### databases 16
>>>![1](pic/67.PNG)
## ###SECURITY安全###
### 设置密码
>>>![1](pic/68.PNG)
## #### LIMITS限制 ###
### maxclients
>>>![1](pic/69.PNG)
>>>![1](pic/70.PNG)
### maxmemory
>>>![1](pic/71.PNG)
### maxmemory-policy
>>>![1](pic/72.PNG)
### maxmemory-samples
>>>![1](pic/73.PNG)
# 五、Redis6的发布和订阅
## 什么是发布和订阅
>>![1](pic/74.PNG)
## Redis的发布和订阅
>>![1](pic/75.PNG)
## 发布订阅命令行实现
>>![1](pic/76.PNG)
>>![1](pic/77.PNG)
# 六、Redis6新数据类型
## Bitmaps
### 简介
>>>![1](pic/78.PNG)
>>>![1](pic/79.PNG)
### 命令
>>>![1](pic/80.PNG)
>>>![1](pic/81.PNG)
>>>![1](pic/82.PNG)
>>>![1](pic/83.PNG)
### Bitmaps与set对比
>>>![1](pic/84.PNG)
>>>![1](pic/85.PNG)
## HyperLogLog
### 简介
>>>![1](pic/86.PNG)
### 命令
>>>![1](pic/87.PNG)
>>>![1](pic/88.PNG)
>>>![1](pic/89.PNG)
## Geospatial
### 简介
>>>![1](pic/90.PNG)
### 命令
>>>![1](pic/91.PNG)
>>>![1](pic/92.PNG)
>>>![1](pic/93.PNG)
# 七、Jedis操作Redis6
## Redis_Jedis_测试
### Jedis所需要的jar包
>>>      <dependency>
>>>          <groupId>redis.clients</groupId>
>>>          <artifactId>jedis</artifactId>
>>>          <version>3.2.0</version>
>>>      </dependency>
### 连接Redis注意事项
>>![1](pic/94.PNG)
### Jedis常用操作
#### 创建动态的工程
#### 创建测试程序
>>>>     import redis.clients.jedis.Jedis;
>>>>     public class Demo01 {
>>>>         public static void main(String[] args) {
>>>>             Jedis jedis = new Jedis("192.168.137.3",6379);
>>>>             String pong = jedis.ping();
>>>>             System.out.println("连接成功："+pong);
>>>>             jedis.close();
>>>>         }
>>>>     }
### 测试相关数据类型
#### Jedis-API:    Key
>>>>     jedis.set("k1", "v1");
>>>>     jedis.set("k2", "v2");
>>>>     jedis.set("k3", "v3");
>>>>     Set<String> keys = jedis.keys("*");
>>>>     System.out.println(keys.size());
>>>>     for (String key : keys) {
>>>>     System.out.println(key);
>>>>     }
>>>>     System.out.println(jedis.exists("k1"));
>>>>     System.out.println(jedis.ttl("k1"));                
>>>>     System.out.println(jedis.get("k1"));
#### Jedis-API:    String
>>>>     jedis.mset("str1","v1","str2","v2","str3","v3");
>>>>     System.out.println(jedis.mget("str1","str2","str3"));
#### Jedis-API:    List
>>>>     List<String> list = jedis.lrange("mylist",0,-1);
>>>>     for (String element : list) {
>>>>     System.out.println(element);
>>>>     }
#### Jedis-API:    set
>>>>     jedis.sadd("orders", "order01");
>>>>     jedis.sadd("orders", "order02");
>>>>     jedis.sadd("orders", "order03");
>>>>     jedis.sadd("orders", "order04");
>>>>     Set<String> smembers = jedis.smembers("orders");
>>>>     for (String order : smembers) {
>>>>     System.out.println(order);
>>>>     }
>>>>     jedis.srem("orders", "order02");
#### Jedis-API:    hash
>>>>     jedis.hset("hash1","userName","lisi");
>>>>     System.out.println(jedis.hget("hash1","userName"));
>>>>     Map<String,String> map = new HashMap<String,String>();
>>>>     map.put("telphone","13810169999");
>>>>     map.put("address","atguigu");
>>>>     map.put("email","abc@163.com");
>>>>     jedis.hmset("hash2",map);
>>>>     List<String> result = jedis.hmget("hash2", "telphone","email");
>>>>     for (String element : result) {
>>>>     System.out.println(element);
>>>>     }
#### Jedis-API:    zset
>>>>     jedis.zadd("zset01", 100d, "z3");
>>>>     jedis.zadd("zset01", 90d, "l4");
>>>>     jedis.zadd("zset01", 80d, "w5");
>>>>     jedis.zadd("zset01", 70d, "z6");
>>>>     Set<String> zrange = jedis.zrange("zset01", 0, -1);
>>>>     for (String e : zrange) {
>>>>     System.out.println(e);
>>>>     }
## Redis_Jedis_实例
### 完成一个手机验证码功能
>>>![1](/pic/95.PNG)
>>>![1](/pic/96.PNG)
# 八、Redis6与SpringBoot整合
## 整合步骤
### 在pom.xml文件中引入redis相关依赖
>>>     <!-- redis -->
>>>     <dependency>
>>>         <groupId>org.springframework.boot</groupId>
>>>         <artifactId>spring-boot-starter-data-redis</artifactId>
>>>     </dependency>
>>>     <!-- spring2.X集成redis所需common-pool2-->
>>>     <dependency>
>>>         <groupId>org.apache.commons</groupId>
>>>         <artifactId>commons-pool2</artifactId>
>>>         <version>2.6.0</version>
>>>     </dependency>
### application.properties配置redis配置
>>>     #Redis服务器地址
>>>     spring.redis.host=192.168.140.136
>>>     #Redis服务器连接端口
>>>     spring.redis.port=6379
>>>     #Redis数据库索引（默认为0）
>>>     spring.redis.database= 0
>>>     #连接超时时间（毫秒）
>>>     spring.redis.timeout=1800000
>>>     #连接池最大连接数（使用负值表示没有限制）
>>>     spring.redis.lettuce.pool.max-active=20
>>>     #最大阻塞等待时间(负数表示没限制)
>>>     spring.redis.lettuce.pool.max-wait=-1
>>>     #连接池中的最大空闲连接
>>>     spring.redis.lettuce.pool.max-idle=5
>>>     #连接池中的最小空闲连接
>>>     spring.redis.lettuce.pool.min-idle=0
### 添加redis配置类(高版本不需要此类，直接在Spring Initializr里勾选Redis)
>>>     @EnableCaching
>>>     @Configuration
>>>     public class RedisConfig extends CachingConfigurerSupport {
>>>     
>>>         @Bean
>>>         public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
>>>             RedisTemplate<String, Object> template = new RedisTemplate<>();
>>>             RedisSerializer<String> redisSerializer = new StringRedisSerializer();
>>>             Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
>>>             ObjectMapper om = new ObjectMapper();
>>>             om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
>>>             om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
>>>             jackson2JsonRedisSerializer.setObjectMapper(om);
>>>             template.setConnectionFactory(factory);
>>>             //key序列化方式
>>>             template.setKeySerializer(redisSerializer);
>>>             //value序列化
>>>             template.setValueSerializer(jackson2JsonRedisSerializer);
>>>             //value hashmap序列化
>>>             template.setHashValueSerializer(jackson2JsonRedisSerializer);
>>>             return template;
>>>         }
>>>     
>>>         @Bean
>>>         public CacheManager cacheManager(RedisConnectionFactory factory) {
>>>             RedisSerializer<String> redisSerializer = new StringRedisSerializer();
>>>             Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
>>>             //解决查询缓存转换异常的问题
>>>             ObjectMapper om = new ObjectMapper();
>>>             om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
>>>             om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
>>>             jackson2JsonRedisSerializer.setObjectMapper(om);
>>>             // 配置序列化（解决乱码的问题）,过期时间600秒
>>>             RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
>>>                     .entryTtl(Duration.ofSeconds(600))
>>>                     .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
>>>                     .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
>>>                     .disableCachingNullValues();
>>>             RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
>>>                     .cacheDefaults(config)
>>>                     .build();
>>>             return cacheManager;
>>>         }
>>>     }
### 测试一下
>>>     @RestController
>>>     @RequestMapping("/redisTest")
>>>     public class RedisTestController {
>>>         @Autowired
>>>         private RedisTemplate redisTemplate;
>>>     
>>>         @GetMapping
>>>         public String testRedis() {
>>>             //设置值到redis
>>>             redisTemplate.opsForValue().set("name","lucy");
>>>             //从redis获取值
>>>             String name = (String)redisTemplate.opsForValue().get("name");
>>>             return name;
>>>         }
>>>     }
# 九、Redis6的事务操作
## Redis_事务_锁机制_秒杀
### Redis的事务定义
>>>![1](/pic/97.PNG)
### Multi、Exec、discard
>>>![1](/pic/98.PNG)
>>>![1](/pic/99.PNG)
### 事务的错误处理
>>>![1](/pic/100.PNG)
>>>![1](/pic/101.PNG)
### 为什么要做成事务
>>>![1](/pic/102.PNG)
### 事务冲突的问题
#### 例子
>>>>![1](/pic/103.PNG)
#### 悲观锁
>>>>![1](/pic/104.PNG)
#### 乐观锁
>>>>![1](/pic/105.PNG)
#### WATCH key [key ...]
>>>>![1](/pic/106.PNG)
>>>>![1](/pic/107.PNG)
#### unwatch
>>>>![1](/pic/108.PNG)
### Redis事务三特性
>>>![1](/pic/109.PNG)
## Redis_事务_秒杀案例
### 解决计数器和人员记录的事务操作
>>>![1](/pic/110.PNG)
### Redis事务--秒杀并发模拟
>>>![1](/pic/111.PNG)
>>>![1](/pic/112.PNG)
>>>![1](/pic/113.PNG)
>>>![1](/pic/114.PNG)
### 超卖问题
>>>![1](/pic/115.PNG)
### 利用乐观锁淘汰用户，解决超卖问题
>>>![1](/pic/116.PNG)
>>>![1](/pic/117.PNG)
### 继续增加并发测试
>>>![1](/pic/118.PNG)
>>>![1](/pic/119.PNG)
>>>![1](/pic/120.PNG)
### 解决库存遗留问题
>>>![1](/pic/121.PNG)
>>>![1](/pic/122.PNG)
### Redis_事务_秒杀案例_代码
>>>![1](/pic/123.PNG)
>>>![1](/pic/124.PNG)
# 十、Redis6持久化之RDB))
# 十一、Redis6持久化之AOF
# 十二、Redis6的主从复制
# 十三、Redis6集群
# 十四、Redis6应用问题解决