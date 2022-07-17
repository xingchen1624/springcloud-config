/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: User
 * Author:   xingc
 * Date:     2020/8/2 16:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.elasticsearch.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉
 * 〈〉
 * @Author xingc
 * @Date 2020/8/2
 * @since 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
    private String name;
    private int age;
}