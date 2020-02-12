package com.shh.crm.interceptor;

import com.shh.crm.domain.Employee;
import com.shh.crm.util.Const;
import com.shh.crm.util.PermissionUtil;
import com.shh.crm.util.ThreadLocalUtil;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ThreadLocalUtil.set(request);
        // 不处理登录请求
        if (request.getRequestURI() != null && request.getRequestURI().contains("login")) {
            return true;
        }
        // 未登录则跳转到登陆页面
        Employee employee = (Employee) request.getSession().getAttribute(Const.USER_IN_SESSION);
        if (employee == null) {
            response.sendRedirect("/login");
            return false;
        }
        // 访问权限校验
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 将请求转换成对应的权限路径（全类名+方法名）
            String resource = handlerMethod.getBean().getClass().getName() + ":" + handlerMethod.getMethod().getName();
            // 校验当前用户是否有该权限
            boolean result = PermissionUtil.checkPermission(resource);
            // 无对应权限
            if (!result) {
                if (handlerMethod.getMethod().isAnnotationPresent(ResponseBody.class)) {
                    // 请求需要返回json
                    response.sendRedirect("/permission/noPermissionJSON");
                } else {
                    // 请求需要返回页面
                    response.sendRedirect("/permission/noPermissionPage");
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

    }
}
