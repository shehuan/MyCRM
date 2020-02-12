package com.shh.crm.service.impl;

import com.shh.crm.domain.Menu;
import com.shh.crm.mapper.MenuMapper;
import com.shh.crm.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryMenu() {
        return menuMapper.queryMenu();
    }
}
