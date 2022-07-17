/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: ShiroConfig
 * Author:   xingc
 * Date:     2021/1/17 16:52
 * Description: Shiro的配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈Shiro的配置类〉
 * @Author xingc
 * @Date 2021/1/17
 * @since 1.0.0
 **/
@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        
        /**
         * 添加shiro的内置过滤
         * anon:无需认证就可以访问
         * authc:必须认证才能访问
         * user:必须有 ‘记住我’功能才能用
         * perms:必须拥有某个资源的权限才能访问
         * role:必须拥有某个角色权限才能访问
         **/
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/shiro/", "anon");
        filterMap.put("/shiro/index", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        
        //授权,正常情况下，未授权会跳转到未授权页面
        filterMap.put("/shiro/shiroAdd", "perms[user:add]");
        filterMap.put("/shiro/shiroUpdate", "perms[user:update]");
        
        //配置规则可以使用通配符
        filterMap.put("/shiro/shiro*", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/shiro/toLogin");
        
        //设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/unAuth");

        return shiroFilterFactoryBean;
    }

    //DefaultWebSecurityManager
    //@Qualifier这里使用这个注解，注入spring容器中的bean，也就是下面的方法定义的bean
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("um") UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager 
                = new DefaultWebSecurityManager();
        //关联UserRealm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    //创建Realm对象，需要自定义类,@Bean默认方法名为bean名，也可以用 name属性指定
    @Bean(name = "um")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    //整合ShiroDialect，用来整合thymeleaf和shiro,可以在thymeleaf页面中使用shiro的语法
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}