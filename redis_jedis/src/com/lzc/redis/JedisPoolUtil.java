/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: TestJedisPool
 * Author:   chen
 * Date:     2019/12/29 15:26
 * Description: 测试redis的连接池
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 〈一句话功能简述〉
 * 〈测试redis的连接池〉
 * --@Author chen
 * --@Date 2019/12/29
 * --@since 1.0.0
 **/
public class JedisPoolUtil {
    /**静态的线程安全的JedisPool对象*/
    private static volatile JedisPool jedisPool = null;

    /**私有的构造方法*/
    private JedisPoolUtil() {}

    /**获取JedisPool对象的公共静态方法*/
    public static JedisPool getJedisPool(){
        /**判断是否为空*/
        if(null == jedisPool){
            /**加同步锁*/
            synchronized (JedisPoolUtil.class){
                if(null == jedisPool){
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxActive(100);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWait(100*1000);
                    poolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(poolConfig,"192.168.111.105",6379);
                }
            }
        }
        return jedisPool;
    }

    /**释放连接池中的连接
     用完连接后释放回连接池中
     */
    public static void release(JedisPool jedisPool, Jedis jedis){
        if (jedis != null) {
            jedisPool.returnResourceObject(jedis);
        }
    }

}