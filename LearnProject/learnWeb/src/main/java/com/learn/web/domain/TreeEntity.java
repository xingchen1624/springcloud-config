/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: TreeEntity
 * Author:   xingc
 * Date:     2020/5/28 21:34
 * Description: 树实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.learn.web.domain;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉
 * 〈树实体类〉
 * @Author xingc
 * @Date 2020/5/28
 * @since 1.0.0
 **/
public class TreeEntity implements Serializable {
    private String id;  //节点的id值
    private String name; //节点的名称
    private Boolean isParent; //是否是父节点
    private String pid; //当前节点对应父节点的id值

    public TreeEntity(String id, String name, Boolean isParent, String pid) {
        this.id = id;
        this.name = name;
        this.isParent = isParent;
        this.pid = pid;
    }

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

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "TreeEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isParent=" + isParent +
                ", pid='" + pid + '\'' +
                '}';
    }
}