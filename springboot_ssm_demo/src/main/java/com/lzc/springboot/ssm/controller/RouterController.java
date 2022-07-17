/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: RouterController
 * Author:   xingc
 * Date:     2021/1/12 21:26
 * Description: 路由控制器controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * 〈一句话功能简述〉
 * 〈路由控制器controller〉
 * @Author xingc
 * @Date 2021/1/12
 * @since 1.0.0
 **/
@Controller
public class RouterController {
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 默认的登录成功后的页面（首页），springsecurity .defaultSuccessUrl("/success")配置
     * @Date 19:17 2021/1/16
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping({"/index","/securitySuccess"})
    public String success(String username, String password, Model model, HttpSession session) {
        return "index";
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 系统登录功能，自定义页面点击登录后的处理方法
     * @Date 19:21 2021/1/16
     * @Param []
     * @return java.lang.String
     **/
   /* @RequestMapping("/userLogin")
    public String sysLogin(String username, String password, Model model, HttpSession session) {
        //登录成功后保存用户session
        session.setAttribute("loginUser",username);
        return "index";
    }*/

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 登录页面
     * @Date 21:28 2021/1/12
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/securityLogin")
    public String toLogin() {
        return "views/login";
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 进入level1页面
     * @Date 21:31 2021/1/12
     * @Param [id]
     * @return java.lang.String
     **/
    @RequestMapping("/level1/{id}")
    public String level1(@PathVariable("id") Integer id) {
        return "views/level1/"+id;
    }
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 进入level2页面
     * @Date 21:31 2021/1/12
     * @Param [id]
     * @return java.lang.String
     **/
    @RequestMapping("/level2/{id}")
    public String level2(@PathVariable("id") Integer id) {
        return "views/level2/"+id;
    }
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 进入level3页面
     * @Date 21:31 2021/1/12
     * @Param [id]
     * @return java.lang.String
     **/
    @RequestMapping("/level3/{id}")
    public String level3(@PathVariable("id") Integer id) {
        return "views/level3/"+id;
    }
}