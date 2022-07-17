package com.learn.web.service;


import com.learn.web.domain.Person;
import com.learn.web.domain.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
    /**
     * 功能描述: <br>
     * @author lzc
     * @Description //TODO 根据用户名查询用户信息
     * @Date 22:35 2020/5/21
     * @param userName:用户名
     * @return User对象
     **/
    User queryUserByName(String userName);

    /**
     * 功能描述: <br>
     * @author lzc
     * @Description //TODO 查询所有用户
     * @Date 21:30 2020/5/22
     * @param
     * @return List<Person>
     **/
    List<Person> queryPersonList();

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据用户名称查询用户id
     * @Date 16:47 2020/7/19
     * @Param [userName]
     * @return java.lang.String
     **/
    String queryUserIdByName(String userName);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据用户Id查询用户部分信息
     * @Date 16:48 2020/7/19
     * @Param [id]
     * @return java.util.List<java.lang.String>
     **/
    Map<String,Object> queryUserInfoById(int id);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 使用resultmap查询
     * @Date 17:39 2020/7/19
     * @Param [id]
     * @return com.lzc.springboot.ssm.domain.User
     **/
    List<User> queryUserInfoById2(int id);
}
