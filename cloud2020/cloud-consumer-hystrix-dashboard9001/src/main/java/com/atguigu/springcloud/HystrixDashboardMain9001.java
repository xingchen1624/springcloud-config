/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: HystrixDashboardMain9001
 * Author:   xingc
 * Date:     2021/4/5 14:24
 * Description: Hystrix的Dashboard主启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 〈一句话功能简述〉
 * 〈Hystrix的Dashboard主启动类〉
 * @author xingc
 * @date 2021/4/5
 * @since 1.0.0
 **/
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }
}