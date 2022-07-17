/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: UserServiceImpl
 * Author:   xingc
 * Date:     2020/5/21 22:38
 * Description: 用户service实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.learn.web.service.impl;

import com.learn.web.dao.UserMapper;
import com.learn.web.domain.Person;
import com.learn.web.domain.User;
import com.learn.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈用户service实现类〉
 * @Author xingc
 * @Date 2020/5/21
 * @since 1.0.0
 **/
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String userName) {
        User user = userMapper.selectUserByName(userName);
        return user;
    }

    @Override
    public List<Person> queryPersonList() {
        List<Person> list = userMapper.queryPersonList();
        return list;
    }

    @Override
    public String queryUserIdByName(String userName) {
        String userId = userMapper.selectUserIdByName(userName);
        return userId;
    }

    @Override
    public Map<String,Object> queryUserInfoById(int id) {
        Map<String,Object> userInfo = userMapper.queryUserInfoById(id);
        return userInfo;
    }

    @Override
    public List<User> queryUserInfoById2(int id) {
        List<User> userInfo = userMapper.queryUserInfoById2(id);
        return userInfo;
    }
}