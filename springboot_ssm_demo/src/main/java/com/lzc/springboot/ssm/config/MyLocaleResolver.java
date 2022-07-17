/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: MyLocaleResolver
 * Author:   xingc
 * Date:     2021/1/2 20:56
 * Description: 自定义区域解析器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * 〈一句话功能简述〉
 * 〈自定义区域解析器〉
 * @Author xingc
 * @Date 2021/1/2
 * @since 1.0.0
 **/
public class MyLocaleResolver implements LocaleResolver {
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 解析区域请求参数
     * @Date 20:59 2021/1/2
     * @Param [httpServletRequest]
     * @return java.util.Locale
     **/
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获取请求中的语言参数
        String lang = httpServletRequest.getParameter("lang");
        //默认语言参数
        Locale aDefault = Locale.getDefault();
        //如果请求参数中 lang不为空，这个参数是login.html页面中切换中文、英文传过来的参数
        Locale locale = null;
        if(!StringUtils.isEmpty(lang)){
            //拆分参数中的语言和国家码
            //zh_CN,en_US
            String[] strings = lang.split("_");
            locale = new Locale(strings[0], strings[1]);
        }else{
            locale = aDefault;
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}