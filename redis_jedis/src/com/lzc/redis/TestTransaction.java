/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: TestTransaction
 * Author:   chen
 * Date:     2019/12/29 14:48
 * Description: 测试redis事务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 〈一句话功能简述〉
 * 〈测试redis事务〉 redis是不完全的事务
 * @Author chen
 * @Date 2019/12/29
 * @since 1.0.0
 **/
public class TestTransaction {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.111.105",6379);
        Transaction multi = jedis.multi();
        multi.set("k4", "v4");
        multi.set("k5", "v5");
        multi.exec();  //执行命令
        //multi.discard();  放弃执行命令
    }
}