/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: UserMapper
 * Author:   xingc
 * Date:     2020/5/21 22:04
 * Description: 用户Mapper接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.dao;

import com.lzc.springboot.ssm.domain.Person;
import com.lzc.springboot.ssm.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈用户Mapper接口〉
 * @Author xingc
 * @Date 2020/5/21
 * @since 1.0.0
 **/
//@Mapper   //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
//@Repository  @Repository注解可加可不加
public interface UserMapper {
    public List<User> selectUserByName(@Param("name") String name);
    public List<Person> queryPersonList();
    public String selectUserIdByName(@Param("userName") String name);
    public Map<String,Object> queryUserInfoById(@Param("userId") int id);
    public List<User> queryUserInfoById2(@Param("userId") int id);

    void addUser(User user);

    void updateUser(User user);

    void delUser(@Param("userId") Integer userId);
}