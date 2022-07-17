/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: TestAPI
 * Author:   chen
 * Date:     2019/12/29 14:24
 * Description: 测试redis的常用api
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 〈一句话功能简述〉
 * 〈测试redis的常用api〉
 * @Author chen
 * @Date 2019/12/29
 * @since 1.0.0
 **/
public class TestAPI {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.111.105",6379);
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");

        System.out.println(jedis.get("k1"));

        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }

        System.out.println("zset---------------------");
        jedis.zadd("zset01", 10, "s1");
        jedis.zadd("zset01", 20, "s2");
        jedis.zadd("zset01", 30, "s3");
        jedis.zadd("zset01", 40, "s4");

        Set<String> zset01 = jedis.zrange("zset01", 0, -1);
        for (String s : zset01) {
            System.out.println(s);
        }
    }
}