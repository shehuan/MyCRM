package com.shh.crm.controller;

import com.shh.crm.domain.Permission;
import com.shh.crm.page.PageResult;
import com.shh.crm.query.PermissionQueryObject;
import com.shh.crm.service.IPermissionService;
import com.shh.crm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(PermissionQueryObject queryObject) {
        return permissionService.queryForPage(queryObject);
    }

    @RequestMapping("/queryPermissionByRoleId")
    @ResponseBody
    public List<Permission> queryPermissionByRoleId(Long roleId) {
        return permissionService.queryPermissionByRoleId(roleId);
    }

    @RequestMapping("/noPermissionPage")
    public String noPermissionPage() {
        return "no_permission";
    }

    @RequestMapping("/noPermissionJSON")
    @ResponseBody
    public Result noPermissionJSON() {
        return new Result(false, "没有访问权限");
    }
}
