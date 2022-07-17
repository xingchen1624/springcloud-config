/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: UserVo
 * Author:   xingc
 * Date:     2021/1/23 14:37
 * Description: Security jdbc认证使用用户类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 〈一句话功能简述〉
 * 〈Security jdbc认证使用用户类〉
 * @Author xingc
 * @Date 2021/1/23
 * @since 1.0.0
 * 
 * 一共创建了三个用户：
 * user： 账号user，密码userpwd，权限为ROLE_USER
 * admin： 账号admin，密码adminpwd，权限为ROLE_ADMIN
 * dba： 账号dba，密码dbapwd，权限为ROLE_DBA,ROLE_USER
 *
 * 注意：这里的role我们跟之前的内存形式的多加了ROLE_前缀。
 * 这是因为之前的role都是通过springsecurity的api赋值过去的，他会自行帮我们加上这个前缀。
 * 但是现在我们使用的是自己的数据库里面读取出来的权限，然后封装到自己的实体类中。
 * 所以这时候需要我们自己手动添加这个ROLE_前缀
 * 
 * 首先我们需要一个实现UserDetails的实体类，UserDetails就是SpringSecurity的认证实体的统一接口
 * 所以我们必须要实现，上面注释的五个方法是UserDetails的方法，具体作用注释上面已经说明了
 * 
 **/
public class UserVo implements UserDetails {
    private long id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * 账号是否失效，返回false账号失效，不可用。
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否被锁，返回false，账号被锁，不可用
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账号认证是否过期，返回false，过期，不可用
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否可用。返回false不可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 返回用户的权限集合。
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}