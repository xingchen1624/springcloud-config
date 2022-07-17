package com.lzc.springboot.ssm.service;

import com.lzc.springboot.ssm.domain.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IShiroUserService {
    /**
     * 功能描述: <br>
     * @author lzc
     * @Description //TODO 根据用户名查询用户信息
     * @Date 22:35 2020/5/21
     * @param userName:用户名
     * @return User对象
     **/
    List<CurrentUser> queryUserByName(String userName);
}
