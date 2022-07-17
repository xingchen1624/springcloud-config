package com.springboot.springboot.mapper;

import com.springboot.springboot.bean.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//指定这是一个操作数据库的mapper
//@Mapper  在springboot的主配置类或者mybatis配置类加上@MapperScan注解，自动
//扫描mapper接口，则不再需要每个接口加 @Mapper注解
public interface DepartmentMapper {

    @Insert("insert into department(dept_name) value(#{deptName})")
    public int insertDept(Department department);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Update("update department set dept_name=#{deptName} where id=#{id}")
    public int updateDept(Department department);

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

}
