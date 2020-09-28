package com.example.service;

import com.example.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    //

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int batchInsert(@Param("list") List<User> list);

    List<User> findByUsername(@Param("username")String username);

}
