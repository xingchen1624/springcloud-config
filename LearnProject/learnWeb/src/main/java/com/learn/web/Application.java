package com.learn.web;
/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: Application
 * Author:   xingc
 * Date:     2020/7/30 22:24
 * Description: 主类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 〈一句话功能简述〉
 * 〈主类〉
 * @Author xingc
 * @Date 2020/7/30
 * @since 1.0.0
 **/
@EnableTransactionManagement
@MapperScan("com.learn.web.dao")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}