/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: MyMvcConfig
 * Author:   xingc
 * Date:     2020/12/29 21:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

/**
 * 〈一句话功能简述〉
 * 〈自定义MVC配置类,扩展springboot对springmvc的现有配置〉
 * @Author xingc
 * @Date 2020/12/29
 * @since 1.0.0
 **/
@Configuration
//@EnableWebMvc  //导入了DelegatingWebMvcConfiguration类，这个类可以从容器中获取所有的WebMvcConfigurer
public class MyMvcConfig implements WebMvcConfigurer {
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 自定义视图解析器
     * @Date 21:33 2020/12/29
     * @Param 
     * @return 
     **/
    @Bean
    public ViewResolver myViewResolver() {
        return new MyViewResolver();
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 添加自定义视图控制
     * @Date 20:28 2020/12/30
     * @Param [registry]
     * @return void
     **/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/firstPage").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 自定义国际化组件
     * @Date 21:15 2021/1/2
     * @Param []
     * @return org.springframework.web.servlet.LocaleResolver
     **/
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 将自定义拦截器注册到spring容器中
     * @Date 21:48 2021/1/3
     * @Param [registry]
     * @return void
     * 说明：为了后续测试和springsecurity框架，先将该拦截器去掉，否则一直被拦截影响测试
     * 使用springsecurity后，其实登录拦截器就不需要了，这些都可以交给springsecurity处理
     **/
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器要拦截的url和排除哪些不用拦截的url(静态资源也要排除，否则也会被拦截)
        //排除/securityLogin和/sysLogin和/securitySuccess用来做测试springsecurity用
        registry.addInterceptor(new LoginHandlerIntercepter()).addPathPatterns("/**")
                .excludePathPatterns("/","/index.html", "/login.html", "/user/login", 
                        "/securityLogin","/securitySuccess","/sysLogin","/firstPage",
                        "main.html","/assets/**","/css/**","/fonts/**","/icons/**","/js/**");
    }*/

    class MyViewResolver implements ViewResolver{
        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            System.out.println(s);
            return null;
        }
        
    }
}