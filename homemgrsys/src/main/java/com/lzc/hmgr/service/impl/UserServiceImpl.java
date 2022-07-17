package com.lzc.hmgr.service.impl;

import com.lzc.hmgr.bo.User;
import com.lzc.hmgr.mapper.UserMapper;
import com.lzc.hmgr.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzc
 * @since 2022-07-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
