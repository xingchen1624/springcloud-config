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
package com.lzc.springboot.ssm.service.impl;

import com.lzc.springboot.ssm.dao.DepartmentDao;
import com.lzc.springboot.ssm.dao.EmployeeDao;
import com.lzc.springboot.ssm.dao.ShiroUserMapper;
import com.lzc.springboot.ssm.dao.UserMapper;
import com.lzc.springboot.ssm.domain.*;
import com.lzc.springboot.ssm.service.IShiroUserService;
import com.lzc.springboot.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
public class ShiroUserServiceImpl implements IShiroUserService {
    @Autowired
    ShiroUserMapper userMapper;

    @Override
    public List<CurrentUser> queryUserByName(String userName) {
        List<CurrentUser> users = userMapper.selectUserByName(userName);
        return users;
    }
}