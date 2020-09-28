package com.example.controller;

import com.example.dao.UserMapper;
import com.example.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
@Api(value = "测试")
public class TestController {

    @Autowired
    private UserMapper userService;

    @ApiOperation(value = "根据ID查询图书名称")
    @RequestMapping(value = {"/findNameByUserID"}, method = RequestMethod.POST)
    public String findNameByUserID(@RequestBody Object vo) {
        User user = userService.selectByPrimaryKey(1072806377661009920L);
        return user.getNickname();
    }
}
