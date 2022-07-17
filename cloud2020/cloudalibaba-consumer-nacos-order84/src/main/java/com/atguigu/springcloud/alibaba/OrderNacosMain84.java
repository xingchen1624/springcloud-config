/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: OrderNacosMain84
 * Author:   xingc
 * Date:     2021/5/10 22:09
 * Description: 84消费者主启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.atguigu.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 〈一句话功能简述〉
 * 〈84消费者主启动类〉
 * @author xingc
 * @date 2021/5/10
 * @since 1.0.0
 **/
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients //开启feign支持
public class OrderNacosMain84 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain84.class, args);
    }

}