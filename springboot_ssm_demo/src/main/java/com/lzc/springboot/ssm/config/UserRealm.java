/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: UserRealm
 * Author:   xingc
 * Date:     2021/1/17 16:55
 * Description: 用户Realm
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.config;

import com.lzc.springboot.ssm.domain.CurrentUser;
import com.lzc.springboot.ssm.domain.User;
import com.lzc.springboot.ssm.service.IShiroUserService;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.util.Collections;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈用户Realm〉自定义的UserRealm   
 * @Author xingc
 * @Date 2021/1/17
 * @since 1.0.0
 **/
public class UserRealm extends AuthorizingRealm {
    
    @Autowired
    IShiroUserService userService;
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 授权
     * @Date 20:46 2021/1/18
     * @Param [principalCollection]
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权doGetAuthorizationInfo");
        //授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //这里的字符串是 ShiroConfig.java中的相关请求授权绑定的字符串
        //filterMap.put("/shiro/shiroAdd", "perms[user:add]");
        //authorizationInfo.addStringPermission("user:add");
        
        //获取当前登录对象
        Subject subject = SecurityUtils.getSubject();
        //通过当前登录对象获取user对象，这里为什么通过该方法获取当前User
        //是因为在认证方法 doGetAuthenticationInfo中，最后返回对象中，往Principal
        //参数中传入了user对象，即第一个参数
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        //设置当前用户的权限
        authorizationInfo.addStringPermission(currentUser.getPerms());

        return authorizationInfo;
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 认证
     * @Date 20:46 2021/1/18
     * @Param [authenticationToken]
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证doGetAuthenticationInfo");
        //转换为controller中封装的UsernamePasswordToken
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        
        //用户名、密码从数据库中取
        List<CurrentUser> currentUsers = userService.queryUserByName(token.getUsername());

        //校验用户名
        CurrentUser user = null;
        if (currentUsers == null || currentUsers.size() == 0) {  //用户不存在
            return null;  //return null会抛出异常UnknownAccountException
        }else{
            user = currentUsers.get(0);
        }

        //根据用户的情况, 来构建 AuthenticationInfo 对象并返回.
        //以下信息是从数据库中获取的.AuthenticationInfo封装着从数据库中查询出来的帐号密码
        //1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        Object principal = user.getUserName();

        //2). credentials: 密码.
        Object credentials = user.getUserPwd();

        //3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
        String realmName = getName();

        //密码认证无需我们处理，由shiro自动处理
        return new SimpleAuthenticationInfo(user,credentials,realmName);
    }

}