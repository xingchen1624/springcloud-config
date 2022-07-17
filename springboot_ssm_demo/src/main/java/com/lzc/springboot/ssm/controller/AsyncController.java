/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: AsyncController
 * Author:   xingc
 * Date:     2021/1/28 21:41
 * Description: 异步任务controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.controller;

import com.lzc.springboot.ssm.service.impl.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

/**
 * 〈一句话功能简述〉
 * 〈异步任务controller〉
 * @author xingc
 * @date 2021/1/28
 * @since 1.0.0
 **/
@RestController
public class AsyncController {
    
    @Autowired
    AsyncService asyncService;
    
    /**
     * 使用/css路径，是防止请求被springsecurity拦截，测试用
     **/
    @GetMapping("/css/hello")
    public String hello() {
        asyncService.hello();
        System.out.println("调用完成~");
        return "ok";
    }
    
    /**
     * 发送邮件异步任务,简单邮件
     **/
    @GetMapping("/css/mail1")
    public String sendMail1() {
        //发送邮件
        asyncService.sendMail1();
        System.out.println("simple邮件发送完成~"+ Calendar.getInstance().getTime());
        return "ok";
    }
    /**
     * 发送邮件异步任务,复杂邮件
     **/
    @GetMapping("/css/mail2")
    public String sendMail2() {
        //发送邮件
        asyncService.sendMail2();
        System.out.println("复杂邮件发送完成~"+ Calendar.getInstance().getTime());
        return "ok";
    }
}