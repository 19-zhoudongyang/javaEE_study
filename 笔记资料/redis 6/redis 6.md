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
# 六、Redis6新数据类型
# 七、Jedis操作Redis6
# 八、Redis6与SpringBoot整合
# 九、Redis6的事务操作
# 十、Redis6持久化之RDB
# 十一、Redis6持久化之AOF
# 十二、Redis6的主从复制
# 十三、Redis6集群
# 十四、Redis6应用问题解决