package com.shh.crm.service;

import com.shh.crm.domain.Permission;
import com.shh.crm.page.PageResult;
import com.shh.crm.query.PermissionQueryObject;

import java.util.List;

public interface IPermissionService {
    PageResult queryForPage(PermissionQueryObject queryObject);

    List<Permission> queryPermissionByRoleId(Long roleId);

    List<Permission> queryAllPermission();

    List<Permission> queryPermissionByEmployeeId(Long employeeId);
}
