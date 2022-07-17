/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: Employee
 * Author:   xingc
 * Date:     2021/1/2 17:31
 * Description: 员工
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈员工〉
 * @Author xingc
 * @Date 2021/1/2
 * @since 1.0.0
 **/
@Data
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    /**
     * 0:女 1:男
     **/
    private Integer gender ;
    private Department department;
    private Date birth;

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 在有参构造器中，将生日字段付了一个默认值
     * @Date 17:56 2021/1/2
     * @Param [id, lastName, email, gender, department]
     * @return 
     **/
    public Employee(Integer id, String lastName, String email, Integer gender, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.birth = new Date();  //默认日期
    }
}