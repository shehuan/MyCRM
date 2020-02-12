package com.shh.crm.util;

import com.shh.crm.domain.Menu;
import org.springframework.util.StringUtils;

import java.util.List;

public class PermissionUtil {
    @SuppressWarnings("unchecked")
    public static boolean checkPermission(String permission) {
        // 获得所有的权限
        List<String> allPermissions = (List<String>) ThreadLocalUtil.get().getSession().getAttribute(Const.ALL_PERMISSION_IN_SESSION);
        // 判断当前访问的路径是否受权限控制
        if (allPermissions.contains(permission)) {
            // 获得当前用户的权限
            List<String> userPermissions = (List<String>) ThreadLocalUtil.get().getSession().getAttribute(Const.USER_PERMISSION_IN_SESSION);
            // 判断当前用户是否有该路径的访问权限
            if (!userPermissions.contains(permission)) {
                // 没有直接匹配到访问权限
                // 再判断该用户是否有all权限
                // 拼接all权限表达式式
                String temp = permission.split(":")[0] + ":all";
                if (!userPermissions.contains(temp)) {
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public static void checkMenuPermission(List<Menu> menu) {
        // 获得当前用户的权限
        List<String> userPermissions = (List<String>) ThreadLocalUtil.get().getSession().getAttribute(Const.USER_PERMISSION_IN_SESSION);
        // 遍历系统菜单，与当前用户拥有的权限进行比对
        for (int i = 0; i < menu.size(); i++) {
            String menuPermission = menu.get(i).getResource();
            // 菜单需要访问权限
            if (!StringUtils.isEmpty(menuPermission)) {
                // 如果用户没有该菜单权限，删除它，这样前台就不会显示
                if (!userPermissions.contains(menuPermission)) {
                    menu.remove(i);
                    i--;
                }
            }
            // else 说明该菜单不要求访问权限

            // 递归处理子菜单
            List<Menu> childrenMenu = menu.get(i).getChildren();
            if (!childrenMenu.isEmpty()) {
                checkMenuPermission(childrenMenu);
            }
        }
    }
}

