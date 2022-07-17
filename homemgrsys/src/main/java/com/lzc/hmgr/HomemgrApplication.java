package com.lzc.hmgr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 这里 @ComponentScan(basePackages = {"com.lzc.springboot.ssm.dao"})不能替代
 * @MapperScan("com.lzc.springboot.ssm.dao")，否则会出现扫描不到Mapper接口的情况
 * @MapperScan是springboot专门用来扫描mybatis的mapper接口的
 *
 * @EnableTransactionManagement：开启事务管理  @MapperScan：扫描mapper类
 **/
@EnableTransactionManagement
@MapperScan("com.lzc.hmgr.mapper")
@SpringBootApplication
public class HomemgrApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomemgrApplication.class, args);
    }

}
