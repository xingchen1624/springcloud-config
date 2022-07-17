/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: IndexController
 * Author:   xingc
 * Date:     2020/8/3 21:35
 * Description: controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.reptile.reptile_jd_data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 〈一句话功能简述〉
 * 〈controller〉
 * @Author xingc
 * @Date 2020/8/3
 * @since 1.0.0
 **/
@Controller
public class IndexController {
    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }
}