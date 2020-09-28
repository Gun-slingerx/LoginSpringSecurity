package com.example.dao;

import com.example.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("roleId") Long roleId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    int batchInsert(@Param("list") List<UserRole> list);

    List<UserRole> findByUserId(@Param("userId")Long userId);


}