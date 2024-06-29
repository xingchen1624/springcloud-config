/**
 * Copyright (C), 2012-2022, by xavier chen
 * FileName: TomcatStartSpringBoot
 * Author:   xingc
 * Date:     2022/7/31 8:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.hmgr;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 〈一句话功能简述〉
 * 〈注意这个类是使用自己的tomcat启动，不使用内嵌tomcat才需要加的，使用内嵌tomcat则不需要该类〉
 * 如果我们不使用内嵌的tomcat，需要添加一个类来启动主类，相当于使用类似web.xml的配置方式来启动spring上下文
 *  tomcat不会主动去启动springboot应用 ，， 所以tomcat启动的时候肯定调用了SpringBootServletInitializer
 *  的SpringApplicationBuilder ，就会启动springboot.
 *  
 *  总结：
 * 1、初始化SpringApplication 从spring.factories 读取 listener ApplicationContextInitializer
 * 2、运行run方法
 * 3、读取 环境变量 配置信息…
 * 4、创建springApplication上下文:ServletWebServerApplicationContext
 * 5、预初始化上下文 ： 读取启动类
 * 6、调用refresh 加载ioc容器
 *      加载所有的自动配置类
 *      创建servlet容器
 *
 * @author xingc
 * @date 2022/7/31
 * @since 1.0.0
 **/
public class TomcatStartSpringBoot extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类 
        return builder.sources(HomemgrApplication.class);
    }
}