/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: ThymeleafController
 * Author:   xingc
 * Date:     2020/5/22 21:02
 * Description: thymeleaf模板测试controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.lzc.springboot.ssm.domain.JsPerson;
import com.lzc.springboot.ssm.domain.Person;
import com.lzc.springboot.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * 〈一句话功能简述〉
 * 〈thymeleaf模板测试controller〉
 * @Author xingc
 * @Date 2020/5/22
 * @since 1.0.0
 **/
@Controller
public class ThymeleafController {
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/queryPersonList")
    public String queryPersons(Model model)
    {
        List<Person> list = userService.queryPersonList();
        model.addAttribute("persons",list);
        return "personList";
    }

    @RequestMapping(value = "/jsParams")
    public String jsParams(Model model)
    {
        return "ajaxJsArrayParam";
    }

    @RequestMapping(value = "/jsArrayRequest")
    @ResponseBody
    public void jsArrayRequest(Model model, HttpServletRequest request)
    {
        String data = request.getParameter("data");
        JSONArray conditionList = JSONArray.parseArray(data);
        System.out.println(conditionList);

        ArrayList<JsPerson> jsPersons = JSON.parseObject(data, new TypeReference<ArrayList<JsPerson>>() {});
        for (JsPerson jsPerson : jsPersons) {
            System.out.println(jsPerson.toString());
        }
    }

    @RequestMapping(value = "getTree")
    public String getTree(){
        //return "ztree_map";
        return "jsonArrayTest";
    }

    @RequestMapping(value = "getJsonParam")
    public String getJsonParam(){
        return "jsonParam";
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 使用map接收json字符串
     * @Date 23:25 2020/8/18
     * @Param [map]
     * @return void
     **/
    @RequestMapping(value = "/jsonRequest")
    @ResponseBody
    public void jsonRequest(@RequestBody(required = false) Map<String,Object> map)
    {
        Object id = map.get("id");
    }
}