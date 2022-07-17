/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: ShiroController
 * Author:   xingc
 * Date:     2021/1/17 16:34
 * Description: Shiro使用的controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉
 * 〈Shiro使用的controller〉
 * @Author xingc
 * @Date 2021/1/17
 * @since 1.0.0
 **/
@Controller
@RequestMapping("/shiro")
public class ShiroController {
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO shiro首页
     * @Date 16:41 2021/1/17
     * @Param [model]
     * @return java.lang.String
     **/
    @RequestMapping({"/","/index"})
    public String shiroIndex(Model model) {
        model.addAttribute("msg", "shiro");
        return "/shiro/shiroIndex";
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 登录功能-基于shiro
     * @Date 20:48 2021/1/18
     * @Param [username, password]
     * @return java.lang.String
     **/
    @RequestMapping("/login")
    public String login(String username,String password,Model model) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //判断当前是否已经认证
        //注意登录认证的时候，用户已经通过注册功能，将用户名密码保存到数据库中，数据库中的密码同样是用相同算法加密后的数据
        if (!subject.isAuthenticated()) {
            // 对密码进行MD5盐值加密
            String enPassword = getEnPassword(username,password);
            
            //封装用户的登录数据
            UsernamePasswordToken usernamePasswordToken
                    = new UsernamePasswordToken(username, enPassword);
            
            //设置记住我
            //usernamePasswordToken.setRememberMe(true);
            
            //登录，如果没有异常说明通过，具体捕获的异常可以参考官方的quickstart里的代码
            try {
                //执行登录
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException uae) {
                model.addAttribute("msg", "用户名错误");
                return "shiro/shiroLogin";
            } catch (IncorrectCredentialsException ice) {
                model.addAttribute("msg", "密码错误");
                return "shiro/shiroLogin";
            } catch (LockedAccountException lae) {
                model.addAttribute("msg", "用户被锁定");
                return "shiro/shiroLogin";
            } catch (AuthenticationException ae) {
                model.addAttribute("msg", "系统错误，请联系系统管理员");
                return "shiro/shiroLogin";
            }
        }
        return "shiro/shiroIndex";
        
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 对密码进行md5盐值加密，盐值可以是用户名，盐值必须是唯一的,单纯的MD5已经不安全
     * @Date 22:24 2021/1/19
     * @Param 
     * @return 
     **/
    public String getEnPassword(String username,String password){
        String hashAlgorithmName = "MD5";  // 加密方式：md5加密
        Object credentials = password;  // 密码
        Object salt = ByteSource.Util.bytes(username);  // 盐
        int hashIterations = 1024;  // 加密次数
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        return result.toString();
    }

    /**
     * 将密码执行加密,第二种加密的方法和上面的方法同样用来做加密，选一个即可
     * @param password 原密码
     * @param salt 盐值
     * @return 加密后的结果
     */
    private String getMd5Password(String password, String salt) {
        // 拼接原密码与盐值
        String str = salt + password + salt;
        // 循环加密5次
        for (int i = 0; i < 5; i++) {
            // DigestUtils：springboot提供的工具类
            str = DigestUtils.md5DigestAsHex(
                    str.getBytes()).toUpperCase();
        }
        // 返回结果
        return str;
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO shiro 测试页面  add
     * @Date 17:14 2021/1/17
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/shiroAdd")
    public String add() {
        return "/shiro/shiroAdd";
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO shiro 测试页面  update
     * @Date 17:14 2021/1/17
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/shiroUpdate")
    public String update() {
        return "/shiro/shiroUpdate";
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 跳转登录页面
     * @Date 17:31 2021/1/17
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/shiro/shiroLogin";
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 未授权提示
     * @Date 22:26 2021/1/20
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/unAuth")
    @ResponseBody
    public String unAuthorized() {
        return "未经授权无法访问该页面！";
    }
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 注销功能
     * @Date 22:30 2021/1/20
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("/logout")
    public String logout() {
        //获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //注销
        currentUser.logout();
        //返回登录页面
        return "/shiro/shiroLogin";
    }
}