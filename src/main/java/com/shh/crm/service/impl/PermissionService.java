package com.shh.crm.service.impl;

import com.shh.crm.domain.Permission;
import com.shh.crm.mapper.PermissionMapper;
import com.shh.crm.page.PageResult;
import com.shh.crm.query.PermissionQueryObject;
import com.shh.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PermissionService implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PageResult queryForPage(PermissionQueryObject queryObject) {
        long count = permissionMapper.queryForPageCount(queryObject);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }

        List<Permission> list = permissionMapper.queryForPage(queryObject);
        return new PageResult(count, list);
    }

    @Override
    public List<Permission> queryPermissionByRoleId(Long roleId) {
        return permissionMapper.queryPermissionByRoleId(roleId);
    }

    @Override
    public List<Permission> queryAllPermission() {
        return permissionMapper.selectAll();
    }

    @Override
    public List<Permission> queryPermissionByEmployeeId(Long employeeId) {
        return permissionMapper.queryPermissionByEmployeeId(employeeId);
    }

}
