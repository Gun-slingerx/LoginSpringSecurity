package com.example.service.impl;

import com.example.dao.UserMapper;
import com.example.dao.UserRoleMapper;
import com.example.domain.Permission;
import com.example.domain.User;
import com.example.domain.UserRole;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public int batchInsert(List<User> list) {
        return userMapper.batchInsert(list);
    }

    @Override
    public List<User> findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public List<Long> getUserPermission(Long userId) {
        //查询用户所属的角色ID列表
        List<UserRole> userRoleList = userRoleMapper.findByUserId(userId);
        if(!CollectionUtils.isEmpty(userRoleList)){
            Set<Long> findKey = new HashSet<>();
            userRoleList.forEach(e -> findKey.add(e.getRoleId()));
            Set permissionIds = redisTemplate.opsForSet().intersect(findKey);
            if(!CollectionUtils.isEmpty(permissionIds)){
                return new ArrayList<>(permissionIds);
            }

        }
        return new ArrayList<>();
    }
}
