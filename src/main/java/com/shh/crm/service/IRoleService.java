package com.shh.crm.service;

import com.shh.crm.domain.Role;
import com.shh.crm.page.PageResult;
import com.shh.crm.query.RoleQueryObject;

import java.util.List;

public interface IRoleService {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    PageResult queryForPage(RoleQueryObject queryObject);

    List<Long> queryRoleByEmployeeId(Long employeeId);
}
