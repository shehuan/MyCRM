package com.shh.crm.mapper;

import com.shh.crm.domain.Employee;
import com.shh.crm.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee getEmployeeForLogin(@Param("username") String username, @Param("password") String password);

    long queryForPageCount(EmployeeQueryObject queryObject);

    List<Employee> queryForPage(EmployeeQueryObject queryObject);

    int updateState(@Param("id") Long id, @Param("state") Integer state);

    int insertRelation(@Param("employeeId") Long employeeId, @Param("roleId") Long roleId);

    int deleteRelationByEmployeeId(Long employeeId);
}