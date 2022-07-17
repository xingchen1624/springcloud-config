/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: UserController
 * Author:   xingc
 * Date:     2020/5/21 22:33
 * Description: 用户controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.controller;

import com.lzc.springboot.ssm.domain.OrgVo;
import com.lzc.springboot.ssm.domain.TreeEntity;
import com.lzc.springboot.ssm.service.ITreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈用户controller〉
 * @Author xingc
 * @Date 2020/5/21
 * @since 1.0.0
 **/
@RestController
public class ZTreeController {
    //依赖注入
    @Autowired
    ITreeService treeService;

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 获取树节点方式一
     * @Date 23:36 2020/5/29
     * @Param []
     * @return java.util.List<com.lzc.springboot.ssm.domain.TreeEntity>
     **/
    @RequestMapping(value = "getTreeData")
    public List<TreeEntity> getTreeData() {
        List<TreeEntity> treeEntities = new ArrayList<TreeEntity>();
        //调用Service层
        List<OrgVo> treeData = treeService.getTreeData();
        List<String> pIds = new ArrayList<String>();
        for (OrgVo orgVo : treeData) {
            if (!StringUtils.isEmpty(orgVo.getpId())){
                pIds.add(orgVo.getpId());
            }
        }
        for (OrgVo orgVo : treeData) {
            if (pIds.contains(orgVo.getOrgId())){
                treeEntities.add(new TreeEntity(orgVo.getOrgId(), orgVo.getOrgName(), true, orgVo.getpId()));
            }else {
                treeEntities.add(new TreeEntity(orgVo.getOrgId(), orgVo.getOrgName(), false, orgVo.getpId()));
            }
        }
//        List<OrgVo> treeData = treeService.getTreeDataById("A1000");
        return treeEntities;
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 获取树节点方式二
     * @Date 23:38 2020/5/29
     * @Param []
     * @return java.util.List<com.lzc.springboot.ssm.domain.TreeEntity>
     **/
    @RequestMapping(value = "getTreeData2")
    public List<TreeEntity> getTreeData2() {
        List<TreeEntity> treeEntities = new ArrayList<TreeEntity>();
        //调用Service层
        List<OrgVo> treeData = treeService.getTreeData();
        for (OrgVo orgVo : treeData) {
            treeEntities.add(new TreeEntity(orgVo.getOrgId(), orgVo.getOrgName(), null, orgVo.getpId()));
        }
        return treeEntities;
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 保存网格
     * @Date 17:27 2020/5/30
     * @Param
     * @return
     **/
    @RequestMapping(value = "saveGridInfo")
    public Map<String,Object> saveGridInfo(HttpServletRequest request) {
        Map<String,Object> result = new HashMap<String, Object>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String gridNo = request.getParameter("gridNo");
        String gridName = request.getParameter("gridName");
        String supGridNo = request.getParameter("supGridNo");
        String location = request.getParameter("lngLat");

        Map<String, String> params = new HashMap<String, String>();
        params.put("gridNo", gridNo);
        params.put("gridName", gridName);
        params.put("supGridNo", supGridNo);
        params.put("location", location);

        treeService.saveGridInfo(params);

        result.put("code","0");
        result.put("gridNo",gridNo);
        result.put("message","success");
        return result;
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据id加载网格经纬度信息
     * @Date 21:49 2020/5/30
     * @Param [id]
     * @return void
     **/
    @RequestMapping(value = "getGridInfo")
    public String getGridInfo(String id){
        String lngLat = treeService.getGridInfo(id);
        return lngLat;
    }

}