package com.anvna.blog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginInterceptor
 * @Description 登录拦截器，防止未登录的用户进入 admin
 * @Author an_vna
 * @Date 2020/10/20 21:07
 * @Version V1.0
 **/

public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 预处理请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if( request.getSession().getAttribute("user") == null ){
            response.sendRedirect("/admin");
            return false;
        }

        return true;
    }
}
