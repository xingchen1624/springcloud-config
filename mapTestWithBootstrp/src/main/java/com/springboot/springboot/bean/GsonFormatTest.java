/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: GsonFormatTest
 * Author:   xingc
 * Date:     2020/4/20 22:28
 * Description: 测试Gsonformat插件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.springboot.springboot.bean;

/**
 * 〈一句话功能简述〉
 * 〈测试Gsonformat插件〉
 * 使用方法，alt+shift+s 点击generate，输入对应的json串，可以直接生成对象，包含get set方法
 * @Author xingc
 * @Date 2020/4/20
 * @since 1.0.0
 **/
public class GsonFormatTest {

    /**
     * status : 0
     * msg : 操作成功
     */

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}