package com.shh.crm.util;

import com.alibaba.fastjson.JSON;
import com.shh.crm.domain.Employee;
import com.shh.crm.domain.Log;
import com.shh.crm.service.ILogService;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 记录访问日志的通知类
 */
public class LogUtil {
    @Autowired
    private ILogService logService;

    public void writeLog(JoinPoint joinPoint) {
        // 避免自己切自己
        if (joinPoint.getTarget() instanceof ILogService) {
            return;
        }
        HttpServletRequest request = ThreadLocalUtil.get();
        Log log = new Log();

        log.setUser((Employee) request.getSession().getAttribute(Const.USER_IN_SESSION));
        log.setIp(request.getRemoteAddr());

        String functionName = joinPoint.getTarget().getClass().getName() + ":" + joinPoint.getSignature().getName();
        log.setFunctionName(functionName);
        String paramsValue = JSON.toJSONString(joinPoint.getArgs());
        log.setParamsValue(paramsValue);

        log.setTime(new Date());

        logService.insert(log);
    }
}
