package com.lzc.springboot.ssm.service;

import com.lzc.springboot.ssm.domain.Department;
import com.lzc.springboot.ssm.domain.Employee;
import com.lzc.springboot.ssm.domain.Person;
import com.lzc.springboot.ssm.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IUserService {
    /**
     * 功能描述: <br>
     * @author lzc
     * @Description //TODO 根据用户名查询用户信息
     * @Date 22:35 2020/5/21
     * @param userName:用户名
     * @return User对象
     **/
    List<User> queryUserByName(String userName);

    /**
     * 功能描述: <br>
     * @author lzc
     * @Description //TODO 查询所有用户
     * @Date 21:30 2020/5/22
     * @param
     * @return List<Person>
     **/
    List<Person> queryPersonList();

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据用户名称查询用户id
     * @Date 16:47 2020/7/19
     * @Param [userName]
     * @return java.lang.String
     **/
    String queryUserIdByName(String userName);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据用户Id查询用户部分信息
     * @Date 16:48 2020/7/19
     * @Param [id]
     * @return java.util.List<java.lang.String>
     **/
    Map<String,Object> queryUserInfoById(int id);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 使用resultmap查询
     * @Date 17:39 2020/7/19
     * @Param [id]
     * @return com.lzc.springboot.ssm.domain.User
     **/
    List<User> queryUserInfoById2(int id);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 获取所有员工信息
     * @Date 21:00 2021/1/4
     * @Param []
     * @return java.util.Collection<com.lzc.springboot.ssm.domain.Employee>
     **/
    Collection<Employee> getAllEmps();

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 获取所有部门信息
     * @Date 21:14 2021/1/7
     * @Param []
     * @return java.util.Collection<com.lzc.springboot.ssm.domain.Department>
     **/
    Collection<Department> getAllDeps();

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 保存员工信息
     * @Date 21:41 2021/1/7
     * @Param [employee]
     * @return void
     **/
    void saveEmp(Employee employee);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据id查询员工信息
     * @Date 16:32 2021/1/9
     * @Param [id]
     * @return com.lzc.springboot.ssm.domain.Employee
     **/
    Employee getEmpById(Integer id);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 删除员工
     * @Date 17:16 2021/1/9
     * @Param [id]
     * @return void
     **/
    void delEmp(Integer id);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 添加用户
     * @Date 21:30 2021/1/11
     * @Param [user]
     * @return void
     **/
    void addUser(User user);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 编辑用户
     * @Date 21:30 2021/1/11
     * @Param [user]
     * @return void
     **/
    void editUser(User user);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 删除用户
     * @Date 21:31 2021/1/11
     * @Param [id]
     * @return void
     **/
    void delUser(Integer id);
}
