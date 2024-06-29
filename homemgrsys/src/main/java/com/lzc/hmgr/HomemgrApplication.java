package com.lzc.hmgr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author chen
 * 这里 @ComponentScan(basePackages = {"com.lzc.springboot.ssm.dao"})不能替代
 * @MapperScan("com.lzc.springboot.ssm.dao")，否则会出现扫描不到Mapper接口的情况
 * @MapperScan是springboot专门用来扫描mybatis的mapper接口的
 *
 * @EnableTransactionManagement：开启事务管理  @MapperScan：扫描mapper类
 * @EnableRedisHttpSession 增加redis session缓存支持
 * maxInactiveIntervalInSeconds ：设置session的过期时间，默认为1800秒，即30分钟
 * 
 **/
@EnableTransactionManagement
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=1800)
/***
 * 开启缓存-spring cache
 **/
@EnableCaching
@MapperScan("com.lzc.hmgr.mapper")
@SpringBootApplication
public class HomemgrApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomemgrApplication.class, args);
    }

}
