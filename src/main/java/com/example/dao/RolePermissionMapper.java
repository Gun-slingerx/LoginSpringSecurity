package com.example.dao;

import com.example.domain.RolePermission;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RolePermissionMapper {
    int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    int batchInsert(@Param("list") List<RolePermission> list);

    List<RolePermission> findByAll();
}