/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: TestMasterSlave
 * Author:   chen
 * Date:     2019/12/29 15:10
 * Description: redis主从复制
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.redis;

import redis.clients.jedis.Jedis;

/**
 * 〈一句话功能简述〉
 * 〈redis主从复制〉
 * @Author chen
 * @Date 2019/12/29
 * @since 1.0.0
 **/
public class TestMasterSlave {
    public static void main(String[] args) {
        Jedis jedisMaster = new Jedis("192.168.111.105",6379);
        Jedis jedisSlave = new Jedis("192.168.111.105",6380);
        jedisSlave.slaveof("192.168.111.105", 6379);
        jedisMaster.set("k1", "I am the lord of Java"); //主写
        System.out.println(jedisSlave.get("k1"));  //从读
    }
}