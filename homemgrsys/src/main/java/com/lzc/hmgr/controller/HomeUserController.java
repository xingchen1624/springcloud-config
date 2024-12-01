package com.lzc.hmgr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzc.hmgr.bo.HomeUser;
import com.lzc.hmgr.bo.User;
import com.lzc.hmgr.mapper.UserMapper;
import com.lzc.hmgr.service.IHomeUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzc
 * @since 2022-07-17
 * @CacheConfig注解用于在类级别上配置缓存的公共属性，避免在每个缓存方法上重复配置相同的
 * 缓存属性。通过使用@CacheConfig注解，可以统一指定缓存的名称、键生成器等属性。
 * 属性：
 * cacheNames ：指定缓存的名称
 * keyGenerator ：指定键生成器
 * tips:在Spring框架中，缓存键生成器（KeyGenerator）负责为缓存中的每个数据项生成唯一的键，
 * 用于在检索时查找数据项。默认情况下，Spring使用SimpleKeyGenerator作为缓存键生成器，
 * 它使用方法的参数作为键
 * 
 */
@RestController
@RequestMapping("/homeUser")
@CacheConfig(cacheNames = "user")
public class HomeUserController {
    @Autowired
    private IHomeUserService userService;
    
    private static Logger logger = Logger.getLogger(HomeUserController.class);
    
    @PostMapping("/login")
    public HomeUser login(@RequestBody HomeUser user, HttpSession session){
        HomeUser homeUser = userService.getById(user.getUserId());
        //将session保存到redis中，共享给其它服务器
        session.setAttribute("loginUser", user);
        logger.debug("欢迎"+user.getUserName()+"登录系统");
        return homeUser;
    }

    /***
     * 获取用户信息列表
     * 注解用于标记一个方法或类支持缓存。当该方法被调用时，Spring会先检查缓存中是否存在相应的结果，
     * 如果存在，则直接返回缓存中的结果，而不执行方法体。如果缓存中不存在结果，则执行方法体，
     * 并将结果存入缓存中供下次使用.
     * 
     * 属性：
     * value：指定缓存的名称或缓存管理器的名称。可以使用cacheNames作为value的别名。如果指定了多个名称，将会使用多个缓存进行缓存操作。
     * key：指定缓存的键。默认情况下，Spring会根据方法的参数生成缓存键。可以使用SpEL表达式来自定义缓存键的生成方式。例如，key = "#param"表示使用方法的参数param作为缓存键。
     *
     * condition：指定一个SpEL表达式，用于判断是否执行缓存操作。只有当表达式的结果为true时，才会执行缓存操作。
     *
     * unless：指定一个SpEL表达式，用于判断是否不执行缓存操作。只有当表达式的结果为false时，才会执行缓存操作。与condition相反。
     *
     * sync：指定是否使用同步模式进行缓存操作。默认值为false，表示使用异步模式。在异步模式下，如果多个线程同时访问同一个缓存项，可能会导致缓存穿透的问题。可以将sync设置为true来避免这个问题。
     *
     * cacheManager：指定使用的缓存管理器的名称。可以通过该属性指定使用不同的缓存管理器。
     *
     * cacheResolver：指定使用的缓存解析器的名称。可以通过该属性指定使用不同的缓存解析器。
     *
     * keyGenerator：指定使用的缓存键生成器的名称。可以通过该属性指定使用不同的缓存键生成器。
     * 
     **/
    @Cacheable(value = "userInfo",key = "#session.id",sync = true)
    @GetMapping("/userList")
    public List<HomeUser> userList(HttpSession session){
        //验证是否可以拿到session
        Object loginUser = session.getAttribute("loginUser");
        String id = session.getId();
        logger.debug("sessionId:"+id);
        StringBuilder s = new StringBuilder("");
        
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            String nextElement = attributeNames.nextElement();
            logger.debug("session中的属性:"+nextElement);
            s.append(nextElement).append(":").append(session.getAttribute(nextElement));
        }
        logger.debug("session中的属性:"+s);

        if(loginUser == null){
            //重新登录，定位到登录页面
            logger.debug("您尚未登录,请登录后再操作~");
            return null;
        }
        List<HomeUser> homeUsers = userService.list();
        homeUsers.forEach(System.out::println);
        logger.debug("家庭成员列表:"+homeUsers);
        return homeUsers;
    }

    /***
     * 根据用户编号查询用户
     * 常用的key写法,更多参考SPEL写法:
     *
     * #user.id : #user指的是方法形参的名称, id指的是user的id属性 , 也就是使用user的id属性作为key ;
     * #user.name: #user指的是方法形参的名称, name指的是user的name属性 ,也就是使用user的name属性作为key ;
     * #result.id : #result代表方法返回值，该表达式 代表以返回对象的id属性作为key ；
     * #result.name : #result代表方法返回值，该表达式 代表以返回对象的name属性作为key ；
     * 
     **/
    @Cacheable(value="userInfo",key = "#userNo")
    @GetMapping("/find/id/{userNo}")
    public HomeUser findByUserNo(@PathVariable("userNo") String userNo) throws InterruptedException {
        HomeUser homeUser = userService.getById(userNo);
        Thread.sleep(3000);
        logger.debug(userNo+"对应家庭成员:"+homeUser);
        return homeUser;
    }

    /***
     * 根据用户名称查询用户
     **/
    @GetMapping("/find/name/{userName}")
    public List<HomeUser> findByUserName(@PathVariable("userName") String userName){
        List<HomeUser> homeUsers = userService.userListByName(userName);
        homeUsers.forEach(System.out::println);
        logger.debug(userName+"家庭成员列表:"+homeUsers);
        return homeUsers;
    }

    /***
     * 根据用户编号删除家庭成员（此方法可以使用软件postman进行测试）
     * @CacheEvict是Spring框架中的一个注解，用于从缓存中移除数据。当使用@CacheEvict注解标记一个方法时，
     * 该方法执行后会触发缓存的清除操作。
     * 属性：
     * value：指定要清除的缓存名称或缓存管理器的名称。可以使用cacheNames作为value的别名。如果指定了多个名称，将会清除多个缓存。
     * key：指定要清除的缓存键。默认情况下，Spring会根据方法的参数生成缓存键。可以使用SpEL表达式来自定义缓存键的生成方式。例如，key = "#param"表示使用方法的参数param作为缓存键。
     * condition：指定一个SpEL表达式，用于判断是否执行缓存清除操作。只有当表达式的结果为true时，才会执行缓存清除操作。
     * allEntries：指定是否清除所有缓存项。默认值为false，表示只清除指定缓存键对应的缓存项。如果将allEntries设置为true，则会清除指定缓存名称或缓存管理器下的所有缓存项。
     * beforeInvocation：指定是否在方法执行之前清除缓存。默认值为false，表示在方法执行之后清除缓存。如果将beforeInvocation设置为true，则会在方法执行之前清除缓存，即使方法执行出现异常。
     * 
     **/
    @CacheEvict(value = "userInfo",allEntries = true)
    @DeleteMapping("/delete/{userNo}")
    public boolean deleteByUserNo(@PathVariable("userNo") String userNo){
        logger.debug(userNo+"对应家庭成员被逻辑删除");
        return  userService.removeById(userNo);
    }

    /***
     * 增加一个家庭成员（此方法可以使用软件postman进行测试）
     * @CachePut是Spring框架中的一个注解，用于将方法的返回值存储到缓存中。
     * 与@Cacheable注解不同的是，@CachePut注解每次都会触发方法的执行，并将结果存储到缓存中。
     * 该注解将信息保存到数据库的同时，也往缓存中缓存一份数据
     **/
    @CachePut(value = "userInfo",key = "#result.userId")
    @PostMapping("/add")
    public HomeUser addUser(@RequestBody HomeUser user){
        logger.debug("新增一个家庭成员:"+user);
        boolean save = userService.save(user);
        if (save) {
            return user;
        }else{
            return null;
        }
    }

    /***
     * 根据工号修改用户信息（此方法可以使用软件postman进行测试）
     **/
    @CachePut(value = "userInfo",key = "#result.userId")
    @PutMapping("/update")
    public HomeUser update(@RequestBody HomeUser user){
        logger.debug("修改一个家庭成员的信息:"+user);
        boolean update = userService.updateById(user);
        if (update) {
            return user;
        }else {
            return null;
        }
    }

    /**
     * 1.分页查询-使用内置的分页方法
     **/
    @GetMapping("/user/page")
    public IPage<HomeUser> page() {
        //分页参数
        Page<HomeUser> page = Page.of(1, 3);
        //查询条件
        QueryWrapper<HomeUser> queryWrapper = new QueryWrapper<>();
        //设置查询字段；仅查询name id字段
        queryWrapper.select("user_id","user_name");
        //添加查询条件
        queryWrapper.gt("age",20);

        Page<HomeUser> homeUserPage = userService.page(page, queryWrapper);
        
        return homeUserPage;
    }

    /**
     * 2.分页查询-使自定义分页查询
     **/
    @GetMapping("/user/page/selfdef")
    public List<HomeUser> pageSelfDef() {
        //分页参数
        Page<HomeUser> page = Page.of(1, 3);
        //查询条件
        HomeUser homeUser = new HomeUser();
        homeUser.setUserName("测");
        homeUser.setAge(20);
        List<HomeUser> homeUsers = userService.selectPageByDto(page, homeUser);
        
        homeUsers.forEach(System.out::println);
        
        return homeUsers;
    }

    /**
     * 通过构造wrapper条件查询
     **/
    @GetMapping("/find/user")
    public void queryByWrapper(){
        //查询一条记录
        QueryWrapper<HomeUser> queryWrapper = new QueryWrapper<>();
        //设置查询字段；仅查询name id字段
        queryWrapper.select("user_id","user_name");
        //添加查询条件
        queryWrapper.eq("user_id",1000);
        HomeUser one = userService.getOne(queryWrapper);
        System.out.println(one);
        
        //组合条件查询
        QueryWrapper<HomeUser> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.select("user_id","user_name");
        queryWrapper1.gt("age", 30);
        queryWrapper1.like("name", "测");
        List<HomeUser> users = userService.list(queryWrapper1);
        users.forEach(user -> System.out.println(user));
        
        //map查询
        Map<String, Object> params = new HashMap<>();
        params.put("address", "山东");
        params.put("birthday", "1991-06-15");
        List<HomeUser> homeUsers = userService.listByMap(params);
        for (HomeUser homeUser : homeUsers) {
            System.out.println(homeUser);
        }
        
        //获取查询数量
        long count = userService.count(queryWrapper1);
        System.out.println(count);

    }
    
}
