package com.example.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 用户表
    */
@ApiModel(value="com-example-domain-User")
@Data
public class User extends Base{
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Long id;

    /**
    * 用户名
    */
    @ApiModelProperty(value="用户名")
    private String username;

    /**
    * 密码
    */
    @ApiModelProperty(value="密码")
    private String password;

    /**
    * 昵称
    */
    @ApiModelProperty(value="昵称")
    private String nickname;

    /**
    * 手机
    */
    @ApiModelProperty(value="手机")
    private String phone;

    /**
    * 邮箱
    */
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
    * 生日
    */
    @ApiModelProperty(value="生日")
    private Long birthday;

    /**
    * 性别，男-1，女-2
    */
    @ApiModelProperty(value="性别，男-1，女-2")
    private Integer sex;

    /**
    * 状态，启用-1，禁用-0
    */
    @ApiModelProperty(value="状态，启用-1，禁用-0")
    private Integer status;

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