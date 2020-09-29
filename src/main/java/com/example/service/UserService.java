package com.example.service;


import com.example.domain.User;
import java.util.List;

public interface UserService {


    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int batchInsert(List<User> list);

    //根据用户名获取用户信息
    List<User> findByUsername(String username);

    //获取用户权限ID列表
    List<Long> getUserPermission(Long userId);
}
