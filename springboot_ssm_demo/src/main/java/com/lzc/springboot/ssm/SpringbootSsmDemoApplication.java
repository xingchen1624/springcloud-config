package com.lzc.springboot.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 功能描述: <br>
 * @Author lzc
 * @Description //TODO
 * @Date 23:25 2020/5/21
 * @Param  
 * @return
 **/
/**
 * 这里 @ComponentScan(basePackages = {"com.lzc.springboot.ssm.dao"})不能替代
 * @MapperScan("com.lzc.springboot.ssm.dao")，否则会出现扫描不到Mapper接口的情况
 * @MapperScan是springboot专门用来扫描mybatis的mapper接口的
 * 
 * @EnableTransactionManagement：开启事务管理  @MapperScan：扫描mapper类
 * @EnableAsync 开启异步注解功能
 * @EnableScheduling 开启定时任务调度
 **/
@EnableTransactionManagement
@MapperScan("com.lzc.springboot.ssm.dao")
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SpringbootSsmDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSsmDemoApplication.class, args);
    }

}
