package com.atguigu.springcloud.alibaba; /**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: com.atguigu.springcloud.alibaba.com.atguigu.springcloud.alibaba.PaymentMain9003
 * Author:   xingc
 * Date:     2021/5/10 21:57
 * Description: 9003服务提供者主启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 〈一句话功能简述〉
 * 〈9003服务提供者主启动类〉
 * @author xingc
 * @date 2021/5/10
 * @since 1.0.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9004.class, args);
    }
}