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

import com.lzc.springboot.ssm.dao.SecurityUserMapper;
import com.lzc.springboot.ssm.domain.MyGrantedAuthority;
import com.lzc.springboot.ssm.domain.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈用户service实现类〉
 * @Author xingc
 * @Date 2020/5/21
 * @since 1.0.0
 **/
@Service
public class SecurityUserServiceImpl implements UserDetailsService {
    @Autowired
    private SecurityUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVo vo = userMapper.selectUserByUsername(username);
        if (vo != null) {
            List<MyGrantedAuthority> authorities = userMapper.selectUserAuthorities(vo.getId());
            vo.setAuthorities(authorities);
        }

        System.out.println(vo.getAuthorities().toString());
        return User.withUserDetails(vo).build();
        //return User.withUsername(username).password(vo.getPassword()).roles("ADMIN","USER").build();
        
        /*return vo == null ? null
            : User.withUserDetails(vo)
            .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
            .build();*/
    }
}