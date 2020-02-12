package com.shh.crm.controller;

import com.shh.crm.domain.Department;
import com.shh.crm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/list")
    @ResponseBody
    public List<Department> list() {
        return departmentService.queryForEmployee();
    }
}
