package com.shh.crm.service.impl;

import com.shh.crm.domain.Permission;
import com.shh.crm.domain.Role;
import com.shh.crm.mapper.RoleMapper;
import com.shh.crm.page.PageResult;
import com.shh.crm.query.RoleQueryObject;
import com.shh.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        roleMapper.deleteRelationByRoleId(id);
        int effectedCount = roleMapper.deleteByPrimaryKey(id);
        return effectedCount;
    }

    @Override
    public int insert(Role record) {
        int effectedCount = roleMapper.insert(record);
        for (Permission permission : record.getPermissions()) {
            // 给中间表插入数据
            roleMapper.insertRelation(record.getId(), permission.getId());
        }
        return effectedCount;
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        int effectedCount = roleMapper.updateByPrimaryKey(record);
        roleMapper.deleteRelationByRoleId(record.getId());
        for (Permission permission : record.getPermissions()) {
            // 给中间表插入数据
            roleMapper.insertRelation(record.getId(), permission.getId());
        }
        return effectedCount;
    }

    @Override
    public PageResult queryForPage(RoleQueryObject queryObject) {
        long count = roleMapper.queryForPageCount(queryObject);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }

        List<Role> list = roleMapper.queryForPage(queryObject);
        return new PageResult(count, list);
    }

    @Override
    public List<Long> queryRoleByEmployeeId(Long employeeId) {
        return roleMapper.queryRoleByEmployeeId(employeeId);
    }
}
