/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: SecurityConfig
 * Author:   xingc
 * Date:     2021/1/12 21:45
 * Description: springsecurity配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.config;

import com.lzc.springboot.ssm.service.impl.SecurityUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 〈一句话功能简述〉
 * 〈springsecurity配置类〉
 * @Author xingc
 * @Date 2021/1/12
 * @since 1.0.0
 * 
 * @EnableWebSecurity开启security功能，里面有@configuration注解
 **/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserServiceImpl userDetailsService;
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 重写WebSecurityConfigurerAdapter中的configure方法
     * @Date 22:09 2021/1/12
     * @Param [http]
     * @return void
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 登录页面、静态资源所有人可以访问，但是里面的功能页只有有权限的人才能访问
         * /shiro/** 这里的shiro是给测试shiro用的，所以特意放行，一般项目里security和shiro用一个就行
         * 设置请求授权的规则
         **/
        http
            .authorizeRequests()
            .antMatchers("/","/login","/index","/shiro/**"
                    ,"/qinjiang/**","/assets/**","/css/**","/fonts/**","/icons/**","/js/**").permitAll()
            .antMatchers("/level1/**").hasRole("ADMIN")
            .antMatchers("/level2/**").hasRole("USER")
            .antMatchers("/level3/**").hasRole("DBA")
            // 任意访问
            .antMatchers("/swagger-ui.html").permitAll() 
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/v2/**").permitAll()
            .antMatchers("/api/**").permitAll()
            //.antMatchers("/csrf").permitAll()
            // 其他地址的访问均需验证权限   
            .anyRequest().authenticated()
            .and()
            //没有权限默认跳到登录页面,需要开启登录页面
            //该方法里默认回去请求  /login
            .formLogin()
            //指定登录页的路径,login页面默认用户名密码参数name为 username和password
            //如果要自定义使用 usernameParameter和 passwordParameter
            .loginPage("/securityLogin")
            //.usernameParameter("uname")
            //.passwordParameter("pwd")
            //指定自定义form表单请求的路径,如果不写springsecurity默认是loginPage配置的值
            //并且这个 /userLogin请求我们不需要自己处理(controller里不需要有对应处理方法)，				springsecurity登录成功默认跳转到"/"请求根路径下，我们可以通过defaultSuccessUrl
            //配置登录成功后的跳转路径
            .loginProcessingUrl("/userLogin")
            .failureUrl("/securityLogin?error")
            //登录成功后的默认跳转页面，如果不设置默认跳转到 "/",http://xxxx:端口号/请求根路径/
            .defaultSuccessUrl("/securitySuccess")
            //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
            //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
            .permitAll();
        
        /*
         * 防止跨网站攻击csrf
         * 默认都会产生一个hiden标签,里面有安全相关的验证,防止请求伪造 
         * 这边我们暂时不需要 可禁用掉
         **/
        http.csrf().disable();
        
        /**
         * 开启 '记住我' 功能
         * 本质是一个cookie，默认保存两周
         **/
        http.rememberMe().rememberMeParameter("remember");
        
        /**
         * 注销，开启注销功能跳转到登录页面
         **/
        http.logout()
                .logoutSuccessUrl("/securityLogin")
                .invalidateHttpSession(true)
                .deleteCookies("testCookie1","testCookie2");
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 认证规则 - 内存认证的方式
     * @Date 15:59 2021/1/16
     * @Param [auth]
     * @return void
     **/
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 设置用户、密码和角色，这里使用了inMemoryAuthentication，从内从中读取
         * 也可以从数据库中读取，正常都是从数据库中读取
         * passwordEncoder设置密码的加密方式，springsecurity 5+版本增加了很多加密方法
         * 有些版本，如果不设置密码加密会报错，有些版本不会，这里要注意
         * and()方法连接多个用户认证，用来做链接使用
         *//*
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .withUser("chen").password(new BCryptPasswordEncoder().encode("123")).roles("vip2", "vip3")
            .and()
            .withUser("root").password(new BCryptPasswordEncoder().encode("111")).roles("vip1", "vip2", "vip3");

    }*/

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 认证规则 - UserDetailsService数据库认证方式
     * @Date 15:43 2021/1/23
     * @Param [auth]
     * @return void
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

}