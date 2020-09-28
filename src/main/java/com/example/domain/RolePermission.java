package com.example.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 角色权限关系表
    */
@ApiModel(value="com-example-domain-RolePermission")
@Data
public class RolePermission {
    /**
    * 角色主键
    */
    @ApiModelProperty(value="角色主键")
    private Long roleId;

    /**
    * 权限主键
    */
    @ApiModelProperty(value="权限主键")
    private Long permissionId;
}