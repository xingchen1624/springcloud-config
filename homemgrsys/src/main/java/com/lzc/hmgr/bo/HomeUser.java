package com.lzc.hmgr.bo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lzc
 * @since 2022-07-17
 */
@Getter
@Setter
@TableName("home_user")
@ApiModel(value = "HomeUser对象", description = "")
public class HomeUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户编号")
    @TableId("user_id")
    private String userId;

    @ApiModelProperty("用户名称")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("出生日期")
    @TableField("birthday")
    private LocalDate birthday;

    @ApiModelProperty("手机号码")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("家庭地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("家庭职责")
    @TableField("duty")
    private String duty;

    /**
     * 逻辑删除（0 未删除、1 删除）
     */
    @ApiModelProperty("是否删除(1-是 0-否)")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_delete",fill = FieldFill.INSERT)
    private String isDelete;

    @ApiModelProperty("创建人")
    @TableField("creator")
    private String creator;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("修改人")
    @TableField("updater")
    private String updater;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
