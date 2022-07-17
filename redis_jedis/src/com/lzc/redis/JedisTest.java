/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: JedisTest
 * Author:   chen
 * Date:     2019/12/28 23:08
 * Description: 测试jedis
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.redis;

import redis.clients.jedis.Jedis;

/**
 * 〈一句话功能简述〉
 * 〈测试jedis〉
 * @Author chen
 * @Date 2019/12/28
 * @since 1.0.0
 **/
public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.159.105",6379);
        System.out.println(jedis.ping());
    }
}