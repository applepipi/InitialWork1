package com.xiaozl.initialwork1.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UrlFilter:过滤不合法路径
 * Author： liping
 * Date: 2017/11/27
 * Time: 21:34
 */
public class UrlFilter implements Filter {

    private FilterConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
       config = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String exclude = config.getInitParameter("exclude");//不用过滤的路径
        if(exclude != null){
            String[] arr = exclude.split(";");
            for(int i = 0; i < arr.length; i++){
                if(arr[i] == null || "".equals(arr[i]))
                    continue;
                if(request.getRequestURI().matches(arr[i])){
                    filterChain.doFilter(servletRequest,servletResponse);//不用过滤就放行
                    return;
                }
            }
            response.sendRedirect("/login");//不合法路径转至login
        }
    }

    public void destroy() {

    }
}
