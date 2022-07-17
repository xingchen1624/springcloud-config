/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: Person
 * Author:   xingc
 * Date:     2020/5/22 21:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.domain;

import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈〉
 * @Author xingc
 * @Date 2020/5/22
 * @since 1.0.0
 **/
public class Person {
    private String id;
    private String name;
    private String location;
    private Integer age;
    private Date birthday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}