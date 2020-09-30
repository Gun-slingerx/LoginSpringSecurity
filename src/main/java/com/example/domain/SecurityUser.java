package com.example.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

@Data
@Slf4j
public class SecurityUser implements UserDetails {

    /**
     * 当前登录用户
     */
    private transient User currentUserInfo;

    /**
     * 当前用户所拥有权限代码
     */
    private transient String permissionIds;

    private List<Long> permissionList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        permissionList.forEach(e -> authorities.add(new SimpleGrantedAuthority(e.toString())));
        return authorities;
    }

    public SecurityUser() {
    }

    public SecurityUser(User user) {
        if (user != null) {
            this.currentUserInfo = user;
        }
    }

    public SecurityUser(User user , List<Long> permissionIdList){
        if (user != null) {
            this.currentUserInfo = user;
            if(!CollectionUtils.isEmpty(permissionIdList)){
                StringJoiner permissionIds = new StringJoiner(",", "[", "]");
                this.permissionList = permissionIdList;
                permissionIdList.forEach(e -> permissionIds.add(e.toString()));
                this.permissionIds = permissionIds.toString();
            }
        }
    }

    @Override
    public String getPassword() {
        return this.getCurrentUserInfo().getPassword();
    }

    @Override
    public String getUsername() {
        return this.getCurrentUserInfo().getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
