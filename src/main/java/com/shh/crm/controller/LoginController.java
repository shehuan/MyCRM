package com.shh.crm.controller;

import com.shh.crm.domain.Employee;
import com.shh.crm.domain.Menu;
import com.shh.crm.domain.Permission;
import com.shh.crm.service.IMenuService;
import com.shh.crm.service.IPermissionService;
import com.shh.crm.util.PermissionUtil;
import com.shh.crm.util.Result;
import com.shh.crm.service.IEmployeeService;
import com.shh.crm.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IMenuService menuService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(String username, String password, HttpSession httpSession) {
        Employee employee = employeeService.getEmployeeForLogin(username, password);
        if (employee == null) {
            return new Result(false, "账号或密码错误");
        }
        httpSession.setAttribute(Const.USER_IN_SESSION, employee);

        // 处理用户权限
        List<Permission> userPermission = permissionService.queryPermissionByEmployeeId(employee.getId());
        List<Permission> allPermission = permissionService.queryAllPermission();
        List<String> userPermissionInSession = new ArrayList<>();
        List<String> allPermissionInSession = new ArrayList<>();
        for (Permission permission : userPermission) {
            userPermissionInSession.add(permission.getResource());
        }
        for (Permission permission : allPermission) {
            allPermissionInSession.add(permission.getResource());
        }
        httpSession.setAttribute(Const.USER_PERMISSION_IN_SESSION, userPermissionInSession);
        httpSession.setAttribute(Const.ALL_PERMISSION_IN_SESSION, allPermissionInSession);

        // 处理菜单
        List<Menu> menu = menuService.queryMenu();
        PermissionUtil.checkMenuPermission(menu);
        httpSession.setAttribute(Const.MENU_IN_SESSION, menu);

        return new Result(true, "登录成功");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
