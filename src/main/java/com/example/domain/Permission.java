package com.example.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 权限表
    */
@ApiModel(value="com-example-domain-Permission")
@Data
public class Permission {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Long id;

    /**
    * 权限名
    */
    @ApiModelProperty(value="权限名")
    private String name;

    /**
    * 类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址
    */
    @ApiModelProperty(value="类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址")
    private String url;

    /**
    * 权限类型，页面-1，按钮-2
    */
    @ApiModelProperty(value="权限类型，页面-1，按钮-2")
    private Integer type;

    /**
    * 权限表达式
    */
    @ApiModelProperty(value="权限表达式")
    private String permission;

    /**
    * 后端接口访问方式
    */
    @ApiModelProperty(value="后端接口访问方式")
    private String method;

    /**
    * 排序
    */
    @ApiModelProperty(value="排序")
    private Integer sort;

    /**
    * 父级id
    */
    @ApiModelProperty(value="父级id")
    private Long parentId;
}