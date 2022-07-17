package com.learn.web.service;

import com.learn.web.domain.OrgVo;

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
