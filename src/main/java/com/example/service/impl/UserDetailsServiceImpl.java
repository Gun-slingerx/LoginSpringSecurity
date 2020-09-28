package com.example.service.impl;

import com.example.dao.UserMapper;
import com.example.dao.UserRoleMapper;
import com.example.domain.SecurityUser;
import com.example.domain.User;
import com.example.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * 1. 用户发起表单请求后，首先进入UsernamePasswordAuthenticationFilter
 * 2.在 UsernamePasswordAuthenticationFilter 中根据用户输入的用户名、密码构建了 UsernamePasswordAuthenticationToken，
 * 并将其交给 AuthenticationManager 来进行认证处理。
 * 3.跳转到ProviderManager，该类是 AuthenticationManager 的实现类
 * 4.当选择好一个合适的 AuthenticationProvider 后，通过 provider.authenticate(authentication) 来让 AuthenticationProvider 进行认证。
 * 5.传统表单登录的 AuthenticationProvider 主要是由 AbstractUserDetailsAuthenticationProvider 来进行处理的，
 * 我们来看下它的 authenticate()方法。
 * 6。etrieveUser() 的具体实现在 DaoAuthenticationProvider 中
 * 7。this.getUserDetailsService().loadUserByUsername(username)调用了UserDetailsService接口的loadUserByUsername(username)方法
 * 以下是自定义的方法实现
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户
        List<User> userList = userMapper.findByUsername(username);
        if (CollectionUtils.isEmpty(userList)) {
            throw new UsernameNotFoundException("User " + " was not found in db");
        }
        User user = userList.get(0);
        Long user_id = user.getId();
        //查询角色
        List<UserRole> userRoleList = userRoleMapper.findByUserId(user_id);
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userRoleList)) {
            /**
             * 在springSecurity中角色和权限是一回事，仅仅使用一段字符串代表权限信息，这里将用户拥有的权限id列表查询出来，并构成userRoleList
             */
            for (UserRole role : userRoleList) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleId().toString());
                grantedAuthorities.add(grantedAuthority);
            }

        }
        return new SecurityUser(user);
    }
}
