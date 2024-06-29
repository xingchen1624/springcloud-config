/**
 * Copyright (C), 2012-2024, by xavier chen
 * FileName: UserInfo
 * Author:   xingc
 * Date:     2024/5/19 18:19
 * Description: 用户vo
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.test;

/**
 * 〈一句话功能简述〉
 * 〈用户vo〉
 * @author xingc
 * @date 2024/5/19
 * @since 1.0.0
 **/
public class UserInfo {
    private Long id;
    private String name;
    private Integer age;

    public UserInfo(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}