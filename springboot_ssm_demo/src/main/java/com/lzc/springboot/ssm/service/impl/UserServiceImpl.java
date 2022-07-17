/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: UserServiceImpl
 * Author:   xingc
 * Date:     2020/5/21 22:38
 * Description: 用户service实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.service.impl;

import com.lzc.springboot.ssm.dao.DepartmentDao;
import com.lzc.springboot.ssm.dao.EmployeeDao;
import com.lzc.springboot.ssm.dao.UserMapper;
import com.lzc.springboot.ssm.domain.Department;
import com.lzc.springboot.ssm.domain.Employee;
import com.lzc.springboot.ssm.domain.Person;
import com.lzc.springboot.ssm.domain.User;
import com.lzc.springboot.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈用户service实现类〉
 * @Author xingc
 * @Date 2020/5/21
 * @since 1.0.0
 **/
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @Override
    public List<User> queryUserByName(String userName) {
        List<User> user = userMapper.selectUserByName(userName);
        return user;
    }

    @Override
    public List<Person> queryPersonList() {
        List<Person> list = userMapper.queryPersonList();
        return list;
    }

    @Override
    public String queryUserIdByName(String userName) {
        String userId = userMapper.selectUserIdByName(userName);
        return userId;
    }

    @Override
    public Map<String,Object> queryUserInfoById(int id) {
        Map<String,Object> userInfo = userMapper.queryUserInfoById(id);
        return userInfo;
    }

    @Override
    public List<User> queryUserInfoById2(int id) {
        List<User> userInfo = userMapper.queryUserInfoById2(id);
        return userInfo;
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 查询所有员工信息
     * @Date 21:12 2021/1/7
     * @Param []
     * @return java.util.Collection<com.lzc.springboot.ssm.domain.Employee>
     **/
    @Override
    public Collection<Employee> getAllEmps() {
        return employeeDao.getAllEmps();
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 获取所有部门信息
     * @Date 21:13 2021/1/7
     * @Param []
     * @return java.util.Collection<com.lzc.springboot.ssm.domain.Department>
     **/
    @Override
    public Collection<Department> getAllDeps() {
        return departmentDao.getAllDeps();
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 保存员工信息
     * @Date 21:42 2021/1/7
     * @Param [employee]
     * @return void
     **/
    @Override
    public void saveEmp(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据id查询员工信息
     * @Date 16:31 2021/1/9
     * @Param [id]
     * @return com.lzc.springboot.ssm.domain.Employee
     **/
    @Override
    public Employee getEmpById(Integer id) {
        Employee emp = employeeDao.getEmpById(id);
        return emp;
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 删除员工
     * @Date 17:17 2021/1/9
     * @Param [id]
     * @return void
     **/
    @Override
    public void delEmp(Integer id) {
        employeeDao.delEmp(id);
    }


    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 添加用户
     * @Date 21:30 2021/1/11
     * @Param [user]
     * @return void
     **/
    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 编辑用户
     * @Date 21:30 2021/1/11
     * @Param [user]
     * @return void
     **/
    @Override
    public void editUser(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 删除用户
     * @Date 21:31 2021/1/11
     * @Param [id]
     * @return void
     **/
    @Override
    public void delUser(Integer id) {
        userMapper.delUser(id);
    }
}