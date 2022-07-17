package com.lzc.hmgr.controller;


import com.lzc.hmgr.bo.HomeUser;
import com.lzc.hmgr.bo.User;
import com.lzc.hmgr.service.IHomeUserService;
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
 * @since 2022-07-17
 */
@RestController
@RequestMapping("/hmgr/home-user")
public class HomeUserController {
    @Autowired
    private IHomeUserService userService;
    private static Logger logger = Logger.getLogger(HomeUserController.class);

    /***
     * 获取用户信息列表
     **/
    @GetMapping("/userList")
    public List<HomeUser> userList(){
        List<HomeUser> homeUsers = userService.list();
        homeUsers.forEach(System.out::println);
        logger.debug("家庭成员列表:"+homeUsers);
        return homeUsers;
    }

    /***
     * 根据用户编号查询用户
     **/
    @GetMapping("/find/id/{userNo}")
    public HomeUser findByUserNo(@PathVariable("userNo") String userNo){
        HomeUser homeUser = userService.getById(userNo);
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
     **/
    @DeleteMapping("/delete/{userNo}")
    public boolean deleteByUserNo(@PathVariable("userNo") String userNo){
        logger.debug(userNo+"对应家庭成员被逻辑删除");
        return  userService.removeById(userNo);
    }

    /***
     * 增加一个家庭成员（此方法可以使用软件postman进行测试）
     **/
    @PostMapping("/add")
    public boolean addUser(@RequestBody HomeUser user){
        logger.debug("新增一个家庭成员:"+user);
        return userService.save(user);
    }

    /***
     * 根据工号修改用户信息（此方法可以使用软件postman进行测试）
     **/
    @PutMapping("/update")
    public boolean update(@RequestBody HomeUser user){
        logger.debug("修改一个家庭成员的信息:"+user);
        return userService.updateById(user);
    }
}
