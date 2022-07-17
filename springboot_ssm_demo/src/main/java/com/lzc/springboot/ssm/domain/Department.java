/**
 * Copyright (C), 2012-2021, by xavier chen
 * FileName: Department
 * Author:   xingc
 * Date:     2021/1/2 17:24
 * Description: 部门类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.lzc.springboot.ssm.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉
 * 〈部门类〉
 * @Author xingc
 * @Date 2021/1/2
 * @since 1.0.0
 **/
//@Api("xxx")等价于 @ApiModel("xxx")
@ApiModel("部门实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @ApiModelProperty("部门id")
    private Integer id;
    @ApiModelProperty("部门名称")
    private String departmentName;
}