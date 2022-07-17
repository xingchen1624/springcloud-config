/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: DepartmentDao
 * Author:   xingc
 * Date:     2021/1/2 17:35
 * Description: 部门Dao
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.dao;

import com.lzc.springboot.ssm.domain.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈部门Dao〉- 不访问数据库，使用map模拟数据库数据
 * @Author xingc
 * @Date 2021/1/2
 * @since 1.0.0
 **/
@Repository
public class DepartmentDao {
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 模拟数据库中的数据map
     **/
    private static Map<Integer, Department> departments = null;
    static {
        departments = new HashMap<Integer, Department>();
        departments.put(101, new Department(101, "研发部"));
        departments.put(102, new Department(102, "运营部"));
        departments.put(103, new Department(103, "后勤部"));
    }
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 获取所有部门数据
     * @Date 17:45 2021/1/2
     * @Param []
     * @return java.util.Collection<com.lzc.springboot.ssm.domain.Department>
     **/
    public Collection<Department> getAllDeps(){
        return departments.values();
    }
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 通过id获取某个部门
     * @Date 17:46 2021/1/2
     * @Param [id]
     * @return com.lzc.springboot.ssm.domain.Department
     **/
    public Department getDepById(Integer id) {
        return departments.get(id);
    }
}