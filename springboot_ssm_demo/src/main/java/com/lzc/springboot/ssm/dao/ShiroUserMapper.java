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
import com.lzc.springboot.ssm.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈shiro用mapper〉
 * @Author xingc
 * @Date 2021/1/18
 * @since 1.0.0
 **/
public interface ShiroUserMapper {
    public List<CurrentUser> selectUserByName(@Param("name") String name);
}