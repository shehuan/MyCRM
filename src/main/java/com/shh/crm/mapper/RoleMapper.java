package com.shh.crm.mapper;

import com.shh.crm.domain.Role;
import com.shh.crm.query.RoleQueryObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    int insertRelation(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    long queryForPageCount(RoleQueryObject queryObject);

    List<Role> queryForPage(RoleQueryObject queryObject);

    int deleteRelationByRoleId(Long roleId);

    List<Long> queryRoleByEmployeeId(Long employeeId);
}