/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: Redis
 * Author:   xingc
 * Date:     2021/2/1 20:44
 * Description: redis测试使用的pojo类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉
 * 〈redis测试使用的pojo类〉
 * 企业开发中大部分pojo类都会实现序列化
 * @author xingc
 * @date 2021/2/1
 * @since 1.0.0
 **/
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Redis implements Serializable {
    private String name;
    private Integer age;
}