/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: User
 * Author:   xingc
 * Date:     2020/5/21 22:02
 * Description: 用户类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉
 * 〈用户类〉
 * @Author xingc
 * @Date 2020/5/21
 * @since 1.0.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String userName;

}