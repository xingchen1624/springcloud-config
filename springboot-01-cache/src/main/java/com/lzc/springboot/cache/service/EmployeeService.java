package com.lzc.springboot.cache.service;

import com.lzc.springboot.cache.bean.Employee;
import com.lzc.springboot.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 功能描述: <br>
 * @Author lzc
 * @Description //TODO @CacheConfig(cacheNames = "emp")这里指定了缓存的名字，就不需要在方法上逐个指定缓存名字了
 * 也就是说方法上的 value = {"emp"}就不再需要了，写上也不影响
 * @Date 22:16 2019/12/19
 * @Param
 * @return
 **/
@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeService {

    //(required = false)不加这个程序运行也没问题，但是employeeMapper下面会有个红色波浪线
    //或者使用 @Resource注解
    @Autowired(required = false)
    EmployeeMapper employeeMapper;

    /**
     * @Cacheable将方法的运行结果进行缓存，以后要是再有相同的数据，直接从缓存中获取，不用调用方法
     * CacheManager中管理多个Cache组件，对缓存的真正CRUD操作在Cache组件中，每个缓存组件都有自己的唯一名字；
     *
     * 几个属性：
     *  CacheName/value:指定存储缓存组件的名字,将方法的返回结果放在哪个缓存当中，数组的方式，
     *  可以指定多个缓存
     *  key:缓存数据使用的key,可以使用它来指定。默认是使用方法参数的值，比如id传进来的值是1，那么1-方法的返回值就是key
     *  也可以使用编写Spel表达式来指定key：#id 参数id的值，等同于#a0/#p0 #root.args[0]
     *  keyGenerator:key的生成器，默认是使用参数值也是默认的生成器，自己可以指定key的生成器的组件id
     *  key/keyGendertor二选一使用
     *
     *  cacheManager指定Cache管理器，或者cacheReslover指定获取解析器
     *  condition:指定符合条件的情况下，才缓存；
     *  unless：否定缓存，unless指定的条件为true，方法的返回值就不会被缓存，可以获取到结果进行判断
     *  sync:是否使用异步模式
     *
     *  运行原理：见笔记
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"emp"},key = "#id",condition = "#id>0 and #a0<10 and #root.methodName eq 'getEmp'",unless = "#result == null")
    public Employee getEmp(Integer id){
        System.out.println("查询id= "+id+"的员工");
        return employeeMapper.getEmpById(id);
    }

    @Cacheable(cacheNames = {"emp"},keyGenerator = "myKeyGenerator")
    public Employee getEmp2(Integer id){
        System.out.println("查询id= "+id+"的员工");
        return employeeMapper.getEmpById(id);
    }

    /**
     * 运行时机
     * 先运行方法，再将目标结果缓存起来
     * cacheable的key是不能使用result的参数的
     *
     * 测试步骤
     * 1、先查询1号员工
     * 2、更新1号员工数据
     * 3、查询1号员工
     * 可能并没有更新，
     * 是因为查询和更新的key不同，所以更新的时候缓存中对应的缓存没有更新。要想更新，要指定查询和更新key相同
     *可以指定 @CachePut(value = {"emp"},key = "#result.id")或者 key = "#employee.id"，#employee传入的参数
     * 功能描述:
     * @Author lzc
     * @Description //TODO @CachePut注解作用：既调用方法又更新换成
     * @Date 21:31 2019/12/19
     * @Param [employee]
     * @return com.lzc.springboot.cache.bean.Employee
     **/
    @CachePut(value = {"emp"},key = "#result.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @CacheEvict的几个常用属性。如果allEntries指定为true，key可以不指定，因为所有内容都被清除
     * allEntries:是否删除value指定的缓存的所有数据，默认是false
     * beforeInvocation=false.缓存的清除是否在方法执行之前。默认是false
     * 默认是在方法执行之后执行，如果该方法执行出现异常，那么缓存不会被清除。如果改为true，那么即使方法异常，缓存仍会被清除
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO
     * @Date 21:56 2019/12/19
     * @Param [id]
     * @return void
     **/
    @CacheEvict(value = "emp",key = "#id",allEntries = true)
    public  void  deleteEmp(Integer id){
        System.out.println("delete的id"+id);
        employeeMapper.deleteEmp(id);
    }

    /**
     * @Caching用来指定复杂的缓存规则，是一个数组，即可以用cacheable、put、evict
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO
     * @Date 22:03 2019/12/19
     * @Param [lastName]
     * @return com.lzc.springboot.cache.bean.Employee
     **/
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.gender")
            }
    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }
}
