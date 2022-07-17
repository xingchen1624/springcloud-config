/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: LoginHandlerIntercepter
 * Author:   xingc
 * Date:     2021/1/3 21:42
 * Description: 登录拦截器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉
 * 〈登录拦截器〉拦截登录请求处理的拦截器类
 * @Author xingc
 * @Date 2021/1/3
 * @since 1.0.0
 **/
public class LoginHandlerIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功后，应该有用户的session
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("msg","尚未登录，请先登录");
            request.getRequestDispatcher("/login.html").forward(request,response);
            return false;
        }
        //放行
        return true;
    }
}