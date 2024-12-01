/**
 * Copyright (C), 2012-2024, by xavier chen
 * FileName: User
 * Author:   xingc
 * Date:     2024/11/30 16:41
 * Description: 人员实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.learn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 〈一句话功能简述〉
 * 〈人员实体类〉
 * @author xingc
 * @date 2024/11/30
 * @since 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String name;
    private String userNo;
    private int age;     
}