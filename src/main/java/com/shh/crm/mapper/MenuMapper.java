package com.shh.crm.mapper;

import com.shh.crm.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
    List<Menu> queryMenu();
}