package com.example.test;

import com.alibaba.fastjson.JSON;
import com.example.dto.LoginDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
//        Test test = new Test();
//        test.passwordEncoder();
//        //加密对象
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String password = "123";  //用户密码
//        //每次密码都不一样
//        String encodePassword = passwordEncoder.encode(password);
//        System.out.println(encodePassword);
//        //但是效验是通过的
//        System.out.println(passwordEncoder.matches(password, encodePassword));
        LoginDto dto = new LoginDto();
        dto.setToken("22222");
        Map<String,String> map = new HashMap();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        dto.setMenus(map);
        String str = JSON.toJSONString(dto);
        System.out.println(str);

    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
