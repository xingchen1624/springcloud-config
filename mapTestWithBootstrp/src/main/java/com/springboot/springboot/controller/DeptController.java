package com.springboot.springboot.controller;

import com.springboot.springboot.bean.Department;
import com.springboot.springboot.mapper.DepartmentMapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DeptController {

    @Resource
    DepartmentMapper departmentMapper;

    @RequestMapping("/getDept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDeptById(id);
    }

    @RequestMapping("/delDept/{id}")
    public int delDept(@PathVariable("id") Integer id){
        return departmentMapper.deleteDeptById(id);
    }

    @RequestMapping("/update/{id}")
    public int updateDept(@PathVariable("id") Integer id){
        return departmentMapper.updateDept(new Department(id, "开发部"));
    }
    //获取
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @GetMapping("/insert")
    public int insertDept(Department department){
        return departmentMapper.insertDept(department);
    }
}
