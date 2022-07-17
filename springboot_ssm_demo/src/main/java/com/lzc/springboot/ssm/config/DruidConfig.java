/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: DruidConfig
 * Author:   xingc
 * Date:     2021/1/10 16:37
 * Description: druid数据源配置文件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈druid数据源配置类〉
 * @Author xingc
 * @Date 2021/1/10
 * @since 1.0.0
 **/
@Configuration
public class DruidConfig {
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 定义自己的druid数据源bean，并绑定配置文件
     * @Date 16:40 2021/1/10
     * @Param []
     * @return javax.sql.DataSource
     **/
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 配置druid后台监控服务器 ServletRegistrationBean
     * 注意：springboot内置了servlet容器，没有web.xml，所以我们想使用servlet可以使用ServletRegistrationBean
     * 来注册servlet的bean
     * @Date 16:43 2021/1/10
     * @Param []
     * @return org.springframework.boot.web.servlet.ServletRegistrationBean
     **/
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> statBean = 
                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //设置用户名、密码等初始化参数
        Map<String, String> map = new HashMap<>();
        //这里的用户名、密码的key是固定的loginUsername和loginPassword，不可修改
        map.put("loginUsername", "admin");
        map.put("loginPassword", "123");
        //设置允许谁可以访问
        map.put("allow", "");
        //禁止谁能访问
        //map.put("deny", "192.xxx.xxx.1");
        
        statBean.setInitParameters(map);
        return statBean;
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 配置druid后台监控过滤器 FilterRegistrationBean
     * @Date 17:29 2021/1/10
     * @Param []
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean
     **/
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        //可以过滤哪些请求，不进行统计
        Map<String, String> map = new HashMap<>();
        map.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        bean.setInitParameters(map);
        return bean;
    }
}