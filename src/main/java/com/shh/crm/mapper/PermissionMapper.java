package com.shh.crm.mapper;

import com.shh.crm.domain.Permission;
import com.shh.crm.query.PermissionQueryObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    long queryForPageCount(PermissionQueryObject queryObject);

    List<Permission> queryForPage(PermissionQueryObject queryObject);

    List<Permission> queryPermissionByRoleId(Long roleId);

    List<Permission> queryPermissionByEmployeeId(Long employeeId);
}