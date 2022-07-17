/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: AsyncService
 * Author:   xingc
 * Date:     2021/1/28 21:40
 * Description: 异步任务服务类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 〈一句话功能简述〉
 * 〈异步任务服务类〉
 * @author xingc
 * @date 2021/1/28
 * @since 1.0.0
 **/
@Service
public class AsyncService {
    
    @Autowired
    JavaMailSenderImpl mailSender;
    
    /**
     * @Async注解标识该方法是个异步方法
     **/
    @Async
    public void hello() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据正在处理~");
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 测试邮件发送,发送简单邮件
     * @Date 16:03 2021/1/30
     * @Param []
     * @return void
     **/
    @Async
    public void sendMail1() {
        /**
         * 简单邮件
         **/
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("测试邮件");
        simpleMailMessage.setText("邮件正文!!!");
        simpleMailMessage.setFrom("848719131@qq.com");
        simpleMailMessage.setTo("848719131@qq.com");
        mailSender.send(simpleMailMessage);
    }
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 测试邮件发送，发送复杂邮件
     * @Date 16:02 2021/1/30
     * @Param []
     * @return void
     **/
    @Async
    public void sendMail2() {
        /**
         * 复杂邮件
         **/
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //组装mimeMessage
        try {
            MimeMessageHelper mimeMessageHelper 
                    = new MimeMessageHelper(mimeMessage,true);
            //标题
            mimeMessageHelper.setSubject("复杂邮件测试");
            //正文，可以支持HTML
            mimeMessageHelper.setText("<h1 style='color:red'>支持HTML</h1>", true);
            //附件,注意这里的第一个参数名必须是文件名，否则发送的附件无法打开
            mimeMessageHelper.addAttachment("kon.jpg", 
                    new File("E:\\图片\\kon.jpg"));
            mimeMessageHelper.addAttachment("sex少女.jpg", 
                    new File("E:\\图片\\sex少女.jpg"));
            
            //发送和接收邮箱
            mimeMessageHelper.setFrom("848719131@qq.com");
            mimeMessageHelper.setTo("848719131@qq.com");
            //发送
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 定时执行任务 cron表达式 秒 分 时 日 月 周几
     *
     * 秒（Seconds）	0~59的整数	, - * /    四个字符
     * 分（Minutes）	0~59的整数	, - * /    四个字符
     * 小时（Hours）	0~23的整数	, - * /    四个字符
     * 日期（DayofMonth）	1~31的整数（但是你需要考虑你月的天数）	,- * ? / L W C  八个字符
     * 月份（Month）	1~12的整数或者 JAN-DEC	, - * /    四个字符
     * 星期（DayofWeek）	1~7的整数或者 SUN-SAT （1=SUN）	, - * ? / L C #   八个字符
     * 年(可选，留空)（Year）	1970~2099	, - * /    四个字符
     *
     * @Date 16:13 2021/1/30
     * @Param []
     * @return void
     **/
    @Scheduled(cron = "15 22 16 * * 6")
    public void scheduledTask() {
        System.out.println("定时执行的任务~");
    }
    
}