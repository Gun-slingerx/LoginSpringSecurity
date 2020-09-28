package com.example.dto;

import com.example.vo.BaseVo;
import lombok.Data;

import java.util.Map;

@Data
public class LoginDto extends BaseVo {

    private Map<String, String> menus;
}
