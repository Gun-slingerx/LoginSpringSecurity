package com.example.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 用户角色关系表
    */
@ApiModel(value="com-example-domain-UserRole")
@Data
public class UserRole {
    /**
    * 用户主键
    */
    @ApiModelProperty(value="用户主键")
    private Long userId;

    /**
    * 角色主键
    */
    @ApiModelProperty(value="角色主键")
    private Long roleId;
}