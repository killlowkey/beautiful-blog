package com.predicated.blog.utils;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ray
 * @date created in 2019/11/15 21:59
 */
public final class WebUtil {
    private WebUtil() {}

    public static String getRequestIp() {
        return getRequest().getRemoteAddr();
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
