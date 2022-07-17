package com.lzc.hmgr.service;

import com.lzc.hmgr.bo.HomeUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzc
 * @since 2022-07-17
 */
public interface IHomeUserService extends IService<HomeUser> {
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 
     * @Date 12:46 2022/7/17
     * @param userName 
     * @return java.util.List<com.lzc.hmgr.bo.HomeUser>
     **/
    List<HomeUser> userListByName(String userName);

}
