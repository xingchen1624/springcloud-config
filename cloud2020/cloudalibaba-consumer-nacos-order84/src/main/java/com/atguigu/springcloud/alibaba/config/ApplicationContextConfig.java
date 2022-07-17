/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: ApplicationContextConfig
 * Author:   xingc
 * Date:     2021/5/10 22:10
 * Description: 配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.atguigu.springcloud.alibaba.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 〈一句话功能简述〉
 * 〈配置类〉
 * @author xingc
 * @date 2021/5/10
 * @since 1.0.0
 **/
@Configuration
public class ApplicationContextConfig {
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 配置RestTemplate http请求工具，并支持负载均衡（@LoadBalanced）
     * @Date 22:12 2021/5/10
     * @Param []
     * @return org.springframework.web.client.RestTemplate
     **/
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}