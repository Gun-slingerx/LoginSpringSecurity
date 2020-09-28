package com.example.service.impl;

import com.example.dao.RolePermissionMapper;
import com.example.domain.RolePermission;
import com.example.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void cacheRolePermissionInfo() {
        List<RolePermission> rolePermissionList = rolePermissionMapper.findByAll();
        if(!CollectionUtils.isEmpty(rolePermissionList)){
            for(RolePermission rolePermission : rolePermissionList){
                redisTemplate.boundSetOps(rolePermission.getRoleId()).add(rolePermission.getPermissionId());
            }
        }
    }
}
