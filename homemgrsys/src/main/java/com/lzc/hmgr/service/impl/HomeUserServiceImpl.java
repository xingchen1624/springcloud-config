package com.lzc.hmgr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzc.hmgr.bo.HomeUser;
import com.lzc.hmgr.mapper.HomeUserMapper;
import com.lzc.hmgr.service.IHomeUserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzc
 * @since 2022-07-17
 * 缓存名，和管理器中配置的一致  @CacheConfig(cacheNames = "user")
 */
@CacheConfig(cacheNames = "user")
@Service
public class HomeUserServiceImpl extends ServiceImpl<HomeUserMapper, HomeUser> implements IHomeUserService {
    /**
     * 这里如果使用@Autowried注解，程序可以正常运行，但是会提示找不到Mapper对应的bean
     * @Mapper 注解是 Mybatis 提供的，而 @Autowried 注解是 Spring 提供的，
     * IDEA能理解 Spring 的上下文，但是不能理解Mybatis。推荐换成@Resource注解
     **/
    @Resource
    HomeUserMapper homeUserMapper;

    /**
     * 根据用户名称查询用户信息
     * key 可为空，指定需按照SpEL编写
     * unless 不缓存的条件 条件为true不缓存，也就是不缓存空数据
     * 
     **/
    @Cacheable
    @Override
    public List<HomeUser> userListByName(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper<HomeUser>();
        queryWrapper.like("user_name", userName);
        List users = homeUserMapper.selectList(queryWrapper);
        return users;
    }

    @Override
    public List<HomeUser> selectPageByDto(Page page, HomeUser userDto) {
        IPage<HomeUser> pageByDto = homeUserMapper.selectPageByDto(page, userDto);
        List<HomeUser> records = pageByDto.getRecords();
        return records;
    }
}
