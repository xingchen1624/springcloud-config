/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: TestController
 * Author:   xingc
 * Date:     2020/7/30 22:22
 * Description: 测试用
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.learn.web.controller;

import com.learn.web.util.ReadImg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 〈一句话功能简述〉
 * 〈测试用〉
 * @Author xingc
 * @Date 2020/7/30
 * @since 1.0.0
 **/
@Controller
public class TestController {
    @RequestMapping("/test")
    public String test(Model model) {
        return "test";
    }
    
    @RequestMapping("/pic")
    @ResponseBody
    public void getPic(){
        String url = "E:\\图片\\猎人.jpg";
        String writeUrl = "E:\\a.png";
        byte[] imageFromLocalByUrl = ReadImg.getImageFromLocalByUrl(url);
        if (null != imageFromLocalByUrl && imageFromLocalByUrl.length > 0) {
            System.out.println("读取到：" + imageFromLocalByUrl.length + " 字节");
            ReadImg.writeImageToDisk(imageFromLocalByUrl, writeUrl);
        } else {
            System.out.println("没有从该连接获得内容");
        }
    }
    
    @RequestMapping("/pic2")
    @ResponseBody
    public void getPic2(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.reset();
        resp.setContentType("application/octet-stream;charset=utf-8");
        resp.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("大图.jpg","UTF-8"));
        
        ServletOutputStream outputStream = null;
        String url = "E:\\图片\\大图.png";
        String writeUrl = "E:\\a.png";
        InputStream inputStream = ReadImg.getImageFromLocalByUrl2(url);
        try {
            outputStream = resp.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }

    }
}