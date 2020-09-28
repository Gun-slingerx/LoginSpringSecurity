package com.example.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 角色表
    */
@ApiModel(value="com-example-domain-Role")
@Data
public class Role {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Long id;

    /**
    * 角色名
    */
    @ApiModelProperty(value="角色名")
    private String name;

    /**
    * 描述
    */
    @ApiModelProperty(value="描述")
    private String description;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Long createTime;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private Long updateTime;
}