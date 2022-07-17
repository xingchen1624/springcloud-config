/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: Content
 * Author:   xingc
 * Date:     2020/8/3 22:11
 * Description: 封装解析的HTML内容的pojo类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.reptile.reptile_jd_data.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉
 * 〈封装解析的HTML内容的pojo类〉
 * @Author xingc
 * @Date 2020/8/3
 * @since 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    private String title;
    private String img;
    private String price;
}