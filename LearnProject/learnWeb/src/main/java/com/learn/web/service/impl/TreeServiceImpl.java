/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: UserServiceImpl
 * Author:   xingc
 * Date:     2020/5/21 22:38
 * Description: 用户service实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.learn.web.service.impl;

import com.learn.web.dao.TreeMapper;
import com.learn.web.domain.OrgVo;
import com.learn.web.service.ITreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈用户service实现类〉
 * @Author xingc
 * @Date 2020/5/21
 * @since 1.0.0
 **/
@Service
public class TreeServiceImpl implements ITreeService {

    @Autowired
    public TreeMapper treeMapper;

    @Override
    public List<OrgVo> getTreeData() {
        List<OrgVo> treeData = treeMapper.getTreeData();
        return treeData;
    }

    @Override
    public List<OrgVo> getTreeDataById(String orgId) {
        return null;
    }

    @Override
    public void saveGridInfo(Map<String, String> params) {
        treeMapper.saveGridInfo(params);
    }

    @Override
    public String getGridInfo(String id) {
        String location = treeMapper.getGridInfo(id);
        if (location == null){
            location = "";
        }
        return location;
    }
}