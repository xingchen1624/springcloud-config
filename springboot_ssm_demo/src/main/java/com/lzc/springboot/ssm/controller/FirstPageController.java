/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: FirstPageController
 * Author:   xingc
 * Date:     2020/12/24 20:51
 * Description: 首页controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.controller;

import com.lzc.springboot.ssm.domain.Department;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * 〈一句话功能简述〉
 * 〈首页controller〉
 * @Author xingc
 * @Date 2020/12/24
 * @since 1.0.0
 **/
//在templates目录下的所有页面，只能通过controller来跳转，不能直接访问
@Controller
public class FirstPageController {
    /**
     * 当我们在自定义配置类MyMvcConfig.java中通过addViewControllers
     * 配置了自定义视图控制，可以将多个url映射到登录页面，这样就不需要下面的
     * 代码了
     **/
    /*@RequestMapping({"/index","/login","/login.html"})
    public String hello(){
        return "login";
    }*/

    @RequestMapping("thymeleaf")
    public String thymeleafTest(Model model) {
        model.addAttribute("msg","<h1>hello thymeleaf!</h1>");
        model.addAttribute("arrays", Arrays.asList(new String[]{"hello","hi"}));
        return "thymeleafTest";
    }
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 登录功能
     * @Date 21:19 2021/1/3
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/user/login")
    public String login(String username, String password, Model model, HttpSession session){
        //具体的业务逻辑
        if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
            //登录跳转
            if ("admin".equals(username) && "1".equals(password)) {
                //登录成功后保存用户session
                session.setAttribute("loginUser",username);
                
                //隐藏真实的请求路径,main.html是虚拟的一个url，在我们自己的视图控制中，将main.html
                //映射到真实要访问的路径
                return "redirect:/main.html";
            }
        }
        //登录失败停在登录页面
        model.addAttribute("msg", "用户名或密码错误，请重试!");
        return "login";
    }
    
    /**
     * 只要我们的接口中返回存在的实体类，它就会被扫描到Swagger中
     **/
    @ApiOperation("Swagger扫描实体类测试方法")
    @PostMapping("/getNvlDep")
    public Department getDep(){
        return new Department();
    }
}