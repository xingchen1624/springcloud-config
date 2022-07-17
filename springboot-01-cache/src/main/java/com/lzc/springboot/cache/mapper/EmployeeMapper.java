package com.lzc.springboot.cache.mapper;


import com.lzc.springboot.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM employee WHERE id = #{id}")
    public Employee getEmpById(Integer id);
    @Update("UPDATE employee SET last_name=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    public void updateEmp(Employee employee);
    @Delete("DELETE FROM employee WHERE employee.id=#{id}")
    public void deleteEmp(Integer id);

    @Select("SELECT * FROM employee WHERE last_name=#{lastName}")
    public Employee getEmpByLastName(String lastName);

    @Insert("insert into employee(lastName,email,gender,d_id) values (#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);
}
