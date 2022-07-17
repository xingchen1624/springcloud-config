/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: UserController
 * Author:   xingc
 * Date:     2020/5/21 22:33
 * Description: 用户controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.controller;

import com.lzc.springboot.ssm.dao.EmployeeDao;
import com.lzc.springboot.ssm.domain.Department;
import com.lzc.springboot.ssm.domain.Employee;
import com.lzc.springboot.ssm.domain.User;
import com.lzc.springboot.ssm.service.IUserService;
import io.swagger.annotations.*;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈用户controller〉
 * @Author xingc
 * @Date 2020/5/21
 * @since 1.0.0
 **/
@Controller
@RequestMapping(value = "/user")
@Api("用户模块API文档")//注解api说明该类需要生成api文档
public class UserController {
    //依赖注入
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/parseUser")
    @ResponseBody
    public void parseUser(HttpServletRequest request) {
        String data = request.getParameter("data");
        JSONArray jsonArray = JSONArray.fromObject(data);
        System.out.println("jsonArray:"+jsonArray);
        List<User> users = (List<User>) JSONArray.toCollection(jsonArray, User.class);

        for (User datum : users) {
            System.out.println(datum.toString());
        }
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 
     * @Date 20:56 2021/1/4
     * @Param [data]
     * @return void
     **/
    @RequestMapping(value = "/parseUser1")
    @ResponseBody
    public void parseUser1(@RequestBody List<User> data) {
        for (User datum : data) {
            System.out.println(datum.toString());
        }
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 
     * @Date 20:57 2021/1/4
     * @Param [data]
     * @return void
     **/
    @RequestMapping(value = "/parseUser2")
    @ResponseBody
    public void parseUser2(@Param("data") String data) {
        System.out.println(data);
    }
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 解析json请求,下面使用map接收json参数，也可以使用bean、list<bean>,或者@RequestParam接收
     * @Date 12:29 2020/7/29
     * @Param []
     * @return void
     **/
    @RequestMapping(value = "/parseJson")
    @ResponseBody
    public void parseJsonRequest(@RequestBody Map<String,Object> params, HttpServletRequest request){
        System.out.println(params);
        params.get("params");
        for (String s : params.keySet()) {
            System.out.println(params.get(s));
        }
        System.out.println("-----------------");
    }

    @RequestMapping("/testSession")
    @ResponseBody
    public User testSession(Model model,HttpServletRequest request,HttpServletResponse response){
        //调用允许跨域方法,此接口支持跨域请求
        crossComain(request, response);
        System.out.println(request.getRequestedSessionId());
        HttpSession session = request.getSession();
        System.out.println("sessionId:"+session.getId());
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        System.out.println(userId);
        System.out.println(userName);
        session.setAttribute("userId","1");
        session.setAttribute("userName","张三");
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        return user;
    }

    /**
     * 支持跨域请求
     * @author fxd
     * @param response
     * @since JDK 1.6
     */
    protected void crossComain(HttpServletRequest request, HttpServletResponse response){
        String[] allowDomains = {"http://localhost:8080","http://127.0.0.1:8080"};
        Set allowOrigins = new HashSet(Arrays.asList(allowDomains));
        String originHeads = request.getHeader("Origin");
        if(allowOrigins.contains(originHeads)){
            //配置跨域访问的最简单的方式是用通配符 * ，（就是不安全，所有的请求都能跨域）
            response.setHeader("Access-Control-Allow-Origin", originHeads);
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,HEAD,PUT,PATCH");
            response.setHeader("Access-Control-Max-Age", "36000");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization,authorization");
            response.setHeader("Access-Control-Allow-Credentials","true");//保持跨域 Ajax 时的 Cookie
        }
    }

    /**
     * 员工管理模块相关controller方法 - 狂神视频代码
     **/
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 获取所有员工信息
     * @Date 21:03 2021/1/4
     * @Param [model]
     * @return java.lang.String
     **/
    @RequestMapping("/empList")
    public String listEmps(Model model) {
        Collection<Employee> allEmps = userService.getAllEmps();
        model.addAttribute("allEmps", allEmps);
        return "emp/empList";
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 跳转添加员工页面 - get请求
     * @Date 21:11 2021/1/7
     * @Param [model]
     * @return java.lang.String
     **/
    @GetMapping("/addEmp")
    public String addEmpPage(Model model) {
        //查出所有的部门信息
        Collection<Department> allDeps = userService.getAllDeps();
        model.addAttribute("deps", allDeps);
        return "emp/addEmp";
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 添加员工，注意这里使用的post请求，虽然和上面的跳转添加页面请求一样
     * @Date 21:21 2021/1/7
     * @Param [model]
     * @return java.lang.String
     **/
    @PostMapping("/addEmp")
    public String addEmp(Employee employee) {
        System.out.println(employee);
        //添加员工
        userService.saveEmp(employee);
        
        //跳转到list页面 redirect,forward
        return "redirect:/user/empList";
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 跳转编辑员工页面
     * @Date 16:30 2021/1/9
     * @Param [id, model]
     * @return java.lang.String
     **/
    @GetMapping("/editEmp/{id}")
    public String editEmpPage(@PathVariable("id") Integer id,Model model) {
        //查询员工信息
        Employee emp = userService.getEmpById(id);
        model.addAttribute("emp",emp);
        //查出所有的部门信息
        Collection<Department> allDeps = userService.getAllDeps();
        model.addAttribute("deps", allDeps);
        return "emp/editEmp";
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 编辑员工信息
     * @Date 17:14 2021/1/9
     * @Param [employee]
     * @return java.lang.String
     **/
    @PostMapping("/editEmp")
    public String editEmp(Employee employee) {
        //因为使用map模拟数据库，所以这里可以直接使用添加的save方法，put到map的时候会覆盖相当于更新
        userService.saveEmp(employee);
        //跳转到list页面 redirect,forward
        return "redirect:/user/empList";
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 删除员工
     * @Date 17:19 2021/1/9
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/delEmp/{id}")
    public String delEmp(@PathVariable("id") Integer id) {
        //删除员工
        userService.delEmp(id);
        return "redirect:/user/empList";
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 注销功能
     * @Date 17:39 2021/1/9
     * @Param [session]
     * @return java.lang.String
     **/
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        //让session失效
        session.invalidate();
        return "redirect:/main.html";
    }
    
    /*
     * ----------------------------狂神视频代码结束-----------------------------
     **/
    
    
    /**
     * 使用mybatis的增删改查 --------------------------- 开始----------------------
     **/
    
    /*
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据用户名称查询用户信息
     * @Date 21:16 2021/1/11
     * @Param 
     * @return 
     **/
    @RequestMapping(value = "/queryUserByName")
    @ResponseBody
    public List<User> queryUser() {
        //调用Service层
        List<User> users = userService.queryUserByName("张三");
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据用户名称查询用户Id
     * @Date 16:42 2020/7/19
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value = "/queryUserIdByName")
    @ResponseBody
    public String queryUserIdByName() {
        //调用Service层
        String userId = userService.queryUserIdByName("张三");
        return userId;
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据用户Id查询用户部分信息
     * @Date 16:42 2020/7/19
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value = "/queryUserInfoById/{id}")
    @ResponseBody
    @ApiOperation("根据用户id查询用户信息")//API中说明该方法的作用
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",//参数名字
                    value = "查询的用户id",//参数的描述
                    required = true,//是否必须传参数，true是
                    paramType = "path",//参数类型 body
                    dataType = "int")//参数类型 int
    })
    public Map<String,Object> 
        queryUserInfoById(@PathVariable("id") Integer id) {
        //调用Service层
        Map<String,Object> userInfo = userService.queryUserInfoById(id);
        return userInfo;
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 使用resultMap映射
     * @Date 17:34 2020/7/19
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping(value = "/queryUserInfoById2")
    @ResponseBody
    public List<User> queryUserInfoById2() {
        //调用Service层
        List<User> userInfo = userService.queryUserInfoById2(1);
        return userInfo;
    }

    //添加用户
    @RequestMapping(value = "/addUser")
    @ResponseBody
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return "add success";
    }

    //编辑用户
    @RequestMapping(value = "/updUser")
    @ResponseBody
    public String updateUser(@RequestBody User user) {
        userService.editUser(user);
        return "update success";
    }

    //删除用户
    @RequestMapping(value = "/delUser/{userId}")
    @ResponseBody
    public String delUser(@PathVariable("userId") Integer id) {
        userService.delUser(id);
        return "delete success";
    }

    /**
     * 使用mybatis的增删改查 --------------------------- 结束----------------------
     **/
    
}