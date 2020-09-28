package com.example.controller;

import com.example.dto.LoginDto;
import com.example.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@Api(value = "后台登录接口")
public class LoginController {

    @ApiOperation(value = "登录")
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public LoginDto login(@RequestBody LoginVo vo) {
        LoginDto dto = new LoginDto();
        return null;
//        return "登录成功";
    }
//    @GetMapping("/login")
//    public ModelAndView login() {
//        return new ModelAndView("login.html");
//    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String loginout(@RequestBody LoginVo vo) {
        return "退出成功";
    }
}
