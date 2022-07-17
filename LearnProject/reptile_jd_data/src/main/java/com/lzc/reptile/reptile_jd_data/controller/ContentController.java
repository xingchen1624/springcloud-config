/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: ContentController
 * Author:   xingc
 * Date:     2020/8/3 22:25
 * Description: content的controller类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.reptile.reptile_jd_data.controller;

import com.lzc.reptile.reptile_jd_data.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈content的controller类〉
 * @Author xingc
 * @Date 2020/8/3
 * @since 1.0.0
 **/
@RestController
public class ContentController {
    @Autowired
    private IContentService contentService;

    @GetMapping("/parse/{keyword}")
    public Boolean parse(@PathVariable("keyword") String keyword) throws Exception {
        Boolean flag = contentService.parseContent(keyword);
        return flag;
    }

    @GetMapping("/search/{pageNo}/{pageSize}/{keyword}")
    public List<Map<String,Object>> search(@PathVariable("keyword") String keyword,
                                           @PathVariable("pageNo") int pageNo,
                                           @PathVariable("pageSize") int pageSize) throws IOException {
        //List<Map<String, Object>> list = contentService.searchPage(keyword, pageNo, pageSize);
        List<Map<String, Object>> list = contentService.searchPageHighLighter(keyword, pageNo, pageSize);
        return list;
    }
}