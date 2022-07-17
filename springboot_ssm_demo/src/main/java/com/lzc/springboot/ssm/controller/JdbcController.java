/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: JdbcController
 * Author:   xingc
 * Date:     2021/1/10 15:34
 * Description: 测试springboot数据库jdbc连接controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈测试springboot数据库jdbc连接controller〉
 * @Author xingc
 * @Date 2021/1/10
 * @since 1.0.0
 **/
@RestController
public class JdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 
     * @Date 15:38 2021/1/10
     * @Param []
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @GetMapping("/userList")
    public List<Map<String, Object>> userList() {
        String sql = "select * from user_info";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        return mapList;
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 添加用户
     * @Date 15:59 2021/1/10
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("addUser")
    public String addUser() {
        String sql = "insert into localtestdb.user_info(user_name) values ('赵六')";
        /**
         * insert和update都可以使用update方法
         **/
        jdbcTemplate.update(sql);
        return "addUser_ok";
    }
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 修改用户
     * @Date 16:08 2021/1/10
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("updateUser/{id}")
    public String updateUser(@PathVariable("id") Integer id) {
        //String sql = "update localtestdb.user_info u set u.user_name='赵七' where u.user_id=4";
        String sql = "update localtestdb.user_info u set u.user_name=? where u.user_id = "+id;
        /**
         * insert和update都可以使用update方法
         **/
        jdbcTemplate.update(sql,new Object[]{"朝气蓬勃"});
        return "updateUser_ok";
    }
    @GetMapping("delUser/{id}")
    public String delUser(@PathVariable("id") Integer id) {
        String sql = "delete from localtestdb.user_info where user_id=?";
        /**
         * insert和update都可以使用update方法
         **/
        jdbcTemplate.update(sql,id);
        return "delUser_ok";
    }
}