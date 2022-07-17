package com.lzc.springboot.ssm.service;

import com.lzc.springboot.ssm.domain.OrgVo;
import com.lzc.springboot.ssm.domain.Person;
import com.lzc.springboot.ssm.domain.User;

import java.util.List;
import java.util.Map;

public interface ITreeService {
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据机构Id获取机构列表
     * @Date 21:38 2020/5/28
     * @Param orgId 机构id
     * @return
     **/
    List<OrgVo> getTreeData();

    List<OrgVo> getTreeDataById(String orgId);

    void saveGridInfo(Map<String, String> params);

    String getGridInfo(String id);
}
