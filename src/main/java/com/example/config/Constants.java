package com.example.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局常用变量
 */
public class Constants {

    /**
     * 接口url
     */
    public static Map<String, String> URL_MAPPING_MAP = new HashMap<>();

    /**
     * 密码加密相关
     */
    public static String SALT = "LR";
    public static final int HASH_ITERATIONS = 1;

    /**
     * 请求头类型：
     * application/x-www-form-urlencoded ： form表单格式
     * application/json ： json格式
     */
    public static final String REQUEST_HEADERS_CONTENT_TYPE = "application/json";

    /**
     * 请求头 - token
     */
    public static final String REQUEST_HEADER = "X-Token";

    /**
     * 登录者角色
     */
    public static final String ROLE_LOGIN = "role_login";
}
