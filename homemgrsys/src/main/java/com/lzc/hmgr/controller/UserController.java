package com.lzc.hmgr.controller;


import com.lzc.hmgr.bo.User;
import com.lzc.hmgr.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzc
 * @since 2022-07-16
 */
@RestController
@RequestMapping("/hmgr/user")
public class UserController {
    @Autowired
    private IUserService userService;
    private static Logger logger = Logger.getLogger(UserController.class);
    
    /***
     * 获取用户信息列表
     **/
    @GetMapping("/userList")
    public List<User> userList(){
        logger.debug("test debug log");
        logger.info("test info log");
        logger.error("test error log");
        return userService.list();
    }

    /***
     * 根据工号查询用户
     **/
    @GetMapping("/find/{userNo}")
    public User findByUserNo(@PathVariable("userNo") String userNo){
        return userService.getById(userNo);
    }

    /***
     * 根据工号删除用户信息（此方法可以使用软件postman进行测试）
     **/
    @DeleteMapping("/delete/{userNo}")
    public boolean deleteByUserNo(@PathVariable("userNo") String userNo){
        return  userService.removeById(userNo);
    }

    /***
     * 增加一个用户信息（此方法可以使用软件postman进行测试）
     **/
    @PostMapping("/add")
    public boolean addUser(@RequestBody User user){
        return userService.save(user);
    }

    /***
     * 根据工号修改用户信息（此方法可以使用软件postman进行测试）
     **/
    @PutMapping("/update")
    public boolean update(@RequestBody User user){
        return userService.updateById(user);
    }
}
