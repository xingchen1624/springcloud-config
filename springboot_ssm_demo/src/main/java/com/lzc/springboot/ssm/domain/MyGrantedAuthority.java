/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: MyGrantedAuthority
 * Author:   xingc
 * Date:     2021/1/23 14:45
 * Description: role的实体类，这个里面就是装着我们的权限
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * 〈一句话功能简述〉
 * 〈role的实体类，这个里面就是装着我们的权限〉
 * @Author xingc
 * @Date 2021/1/23
 * @since 1.0.0
 **/
public class MyGrantedAuthority implements GrantedAuthority {
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return authority;
    }
}