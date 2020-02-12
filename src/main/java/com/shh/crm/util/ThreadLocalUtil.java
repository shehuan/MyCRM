package com.shh.crm.util;

import javax.servlet.http.HttpServletRequest;

public class ThreadLocalUtil {
    private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();

    public static void set(HttpServletRequest request) {
        threadLocal.set(request);
    }

    public static HttpServletRequest get() {
        return threadLocal.get();
    }
}
