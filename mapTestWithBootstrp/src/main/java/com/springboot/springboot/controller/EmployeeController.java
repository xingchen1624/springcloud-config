package com.springboot.springboot.controller;

import com.springboot.springboot.bean.Employee;
import com.springboot.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EmployeeController {
    @Resource
    EmployeeMapper employeeMapper;

    @RequestMapping("/getEmp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeMapper.getEmpById(id);
    }

    @GetMapping("/insertEmp")
    public Employee insertEmp(Employee employee){
        employeeMapper.insetEmp(employee);
        return employee;
    }
}
