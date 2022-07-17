package com.springboot.springboot.mapper;

import com.springboot.springboot.bean.Employee;

public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insetEmp(Employee employee);
}
