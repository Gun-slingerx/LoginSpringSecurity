package com.example.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Base {
    /**
     * 盐值
     */
    @ApiModelProperty(value = "盐值")
    private String salt;

    /**
     * token
     */
    @ApiModelProperty(value = "token")
    private String token;
}
