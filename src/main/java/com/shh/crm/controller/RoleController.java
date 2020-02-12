package com.shh.crm.controller;

import com.shh.crm.domain.Role;
import com.shh.crm.page.PageResult;
import com.shh.crm.query.RoleQueryObject;
import com.shh.crm.service.IRoleService;
import com.shh.crm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/page")
    public String page() {
        return "role";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(RoleQueryObject queryObject) {
        return roleService.queryForPage(queryObject);
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result save(Role role) {
        Result result = new Result();
        try {
            roleService.insert(role);
            result.setMessage("保存成功");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("保存失败，请联系管理员");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Role role) {
        Result result = new Result();
        try {
            roleService.updateByPrimaryKey(role);
            result.setMessage("更新成功");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新失败，请联系管理员");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
            roleService.deleteByPrimaryKey(id);
            result.setMessage("删除成功");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除失败，请联系管理员");
        }
        return result;
    }

    @RequestMapping("/queryAllRole")
    @ResponseBody
    public List<Role> queryAllRole() {
        return roleService.selectAll();
    }

    @RequestMapping("/queryRoleByEmployeeId")
    @ResponseBody
    public List<Long> queryRoleByEmployeeId(Long employeeId) {
        return roleService.queryRoleByEmployeeId(employeeId);
    }
}
