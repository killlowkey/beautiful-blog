package com.predicated.blog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 * @author Ray
 * @date created in 2019/11/16 10:47
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        /**
         * 拦截未登录的用户：判断 session 中的 user 属性
         */
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin/login");
            return false;
        }
        return true;
    }

}
