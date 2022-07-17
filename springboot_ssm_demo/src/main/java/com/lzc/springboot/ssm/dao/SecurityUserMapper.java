/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: ShiroUserMapper
 * Author:   xingc
 * Date:     2021/1/18 21:33
 * Description: shiro用mapper
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.dao;

import com.lzc.springboot.ssm.domain.CurrentUser;
import com.lzc.springboot.ssm.domain.MyGrantedAuthority;
import com.lzc.springboot.ssm.domain.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈Security使用的mapper〉
 * @Author xingc
 * @Date 2021/1/18
 * @since 1.0.0
 **/
public interface SecurityUserMapper {
    /**
     * 根据账号查询用户。
     *
     * @param username
     * @return
     */
    UserVo selectUserByUsername(String username);

    /**
     * 根据用户查找权限
     *
     * @param id
     * @return
     */
    List<MyGrantedAuthority> selectUserAuthorities(long id);
}