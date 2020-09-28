package com.example.config;

import com.example.dao.UserMapper;
import com.example.domain.SecurityUser;
import com.example.domain.User;
import com.example.service.impl.UserDetailsServiceImpl;
import com.example.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.security.Security;

/**
 * 自定义认证处理
 */
@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //进入认证流程
        // 获取前端表单中输入后返回的用户名、密码
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        //查询此账号数据库中的数据
        SecurityUser securityUser = (SecurityUser) userDetailsService.loadUserByUsername(userName);
        boolean isValid = PasswordUtils.isValidPassword(password, securityUser.getPassword(), securityUser.getCurrentUserInfo().getSalt());
        // 验证密码
        if (!isValid) {
            throw new BadCredentialsException("密码错误！");
        }

        // 前后端分离情况下 处理逻辑...
        // 更新登录令牌 - 之后访问系统其它接口直接通过token认证用户权限...
        String token = PasswordUtils.encodePassword(System.currentTimeMillis() + securityUser.getCurrentUserInfo().getSalt(), securityUser.getCurrentUserInfo().getSalt());
        //TODO 将token 以及用户信息存入缓存并设定失效时间

        User user = userMapper.selectByPrimaryKey(securityUser.getCurrentUserInfo().getId());
        user.setToken(token);
        userMapper.updateByPrimaryKey(user);
        securityUser.getCurrentUserInfo().setToken(token);
        return new UsernamePasswordAuthenticationToken(securityUser, password, securityUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
