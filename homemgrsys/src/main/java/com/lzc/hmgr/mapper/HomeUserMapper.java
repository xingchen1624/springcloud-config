package com.lzc.hmgr.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzc.hmgr.bo.HomeUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lzc
 * @since 2022-07-17
 */
@Mapper
public interface HomeUserMapper extends BaseMapper<HomeUser> {
    /**
     * 支持分页的dto条件查询
     * @param page
     * @param userDto
     * @return
     **/
    IPage<HomeUser> selectPageByDto(IPage<HomeUser> page, @Param("userDto") HomeUser userDto);

}
