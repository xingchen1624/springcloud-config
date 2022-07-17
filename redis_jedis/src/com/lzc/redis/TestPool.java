/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: TestPool
 * Author:   chen
 * Date:     2019/12/29 15:52
 * Description: 测试redis连接池
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 〈一句话功能简述〉
 * 〈测试Jedis连接池〉
 * @Author chen
 * @Date 2019/12/29
 * @since 1.0.0
 **/
public class TestPool {
    public static void main(String[] args) {
        //单例模式获取连接池
        JedisPool jedisPool = JedisPoolUtil.getJedisPool();
        Jedis jedis = null;
        //从连接池中获取jedis实例
        try {
            jedis = jedisPool.getResource();
            jedis.set("k101", "v101");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JedisPoolUtil.release(jedisPool,jedis);
        }
    }
}