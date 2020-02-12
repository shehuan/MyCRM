package com.shh.crm.controller;

import com.shh.crm.domain.Menu;
import com.shh.crm.util.Const;
import com.shh.crm.util.ThreadLocalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/menu")
    @ResponseBody
    public List<Menu> menu() {
        return (List<Menu>) ThreadLocalUtil.get().getSession().getAttribute(Const.MENU_IN_SESSION);
    }
}
