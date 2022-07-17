/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: User
 * Author:   xingc
 * Date:     2020/5/21 22:02
 * Description: 用户类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.learn.web.domain;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉
 * 〈用户类〉
 * @Author xingc
 * @Date 2020/5/21
 * @since 1.0.0
 **/
public class User {
    private String userId;
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}