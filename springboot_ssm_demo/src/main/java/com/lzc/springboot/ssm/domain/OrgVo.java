/**
 * Copyright (C), 2012-2020, by xavier chen
 * FileName: OrgVo
 * Author:   xingc
 * Date:     2020/5/28 21:37
 * Description: 机构实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.domain;

/**
 * 〈一句话功能简述〉
 * 〈机构实体类〉
 * @Author xingc
 * @Date 2020/5/28
 * @since 1.0.0
 **/
public class OrgVo {
    private String orgId;
    private String orgName;
    private String pId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "OrgVo{" +
                "orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", pId='" + pId + '\'' +
                '}';
    }
}