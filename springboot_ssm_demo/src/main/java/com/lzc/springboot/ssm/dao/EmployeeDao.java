/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: EmployeeDao
 * Author:   xingc
 * Date:     2021/1/2 17:47
 * Description: 员工dao
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.dao;

import com.lzc.springboot.ssm.domain.Department;
import com.lzc.springboot.ssm.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈员工dao〉
 * @Author xingc
 * @Date 2021/1/2
 * @since 1.0.0
 **/
@Repository
public class EmployeeDao {
    /**
     * 员工所属部门
     **/
    @Autowired
    private DepartmentDao departmentDao;
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 自增主键
     * @Date 17:59 2021/1/2
     * @Param 
     * @return 
     **/
    private static Integer initId = 104;
    
    //模拟数据库中的数据map
    private static Map<Integer, Employee> employees = null;
    static {
        employees = new HashMap<Integer, Employee>();
        employees.put(101, new Employee(101,"AA","aaaa1@163.com",1,new Department(101,"研发部")));
        employees.put(102, new Employee(102,"BB","aaaa2@163.com",0,new Department(102,"运营部")));
        employees.put(103, new Employee(103,"CC","aaaa3@163.com",0,new Department(103,"后勤部")));
    }

    /**
     * 功能描述: <br>
     *
     * @return
     * @Author lzc
     * @Description //TODO 增加一个员工
     * @Date 17:59 2021/1/2
     * @Param
     **/
    public void addEmployee(Employee employee) {
        if(employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 获取全部员工
     * @Date 18:11 2021/1/2
     * @Param []
     * @return java.util.Collection<com.lzc.springboot.ssm.domain.Employee>
     **/
    public Collection<Employee> getAllEmps() {
        return employees.values();
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 通过id查询员工信息
     * @Date 18:11 2021/1/2
     * @Param [id]
     * @return com.lzc.springboot.ssm.domain.Employee
     **/
    public Employee getEmpById(Integer id) {
        return employees.get(id);
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 删除指定员工
     * @Date 18:12 2021/1/2
     * @Param [id]
     * @return void
     **/
    public void delEmp(Integer id) {
        employees.remove(id);
    }
}