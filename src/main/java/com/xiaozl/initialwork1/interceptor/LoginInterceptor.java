package com.xiaozl.initialwork1.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * LoginInterceptor：登录拦截器
 * Date: 2017/11/27
 * Time: 20:19
 */
public class LoginInterceptor implements HandlerInterceptor{
    private List<String> excludeUrl;

    public void setExcludeUrl(List<String> list){
        this.excludeUrl = list;
    }
    public List<String> getExcludeUrl(){
        return this.excludeUrl;
    }

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //对除了login和signIn之外的请求拦截，登录了放行，否则转至login.jsp
        String requestUri = httpServletRequest.getRequestURI();
        if(requestUri.startsWith(httpServletRequest.getContextPath())){
            requestUri = requestUri.substring(httpServletRequest.getContextPath().length(), requestUri.length());
        }
        //系统根目录
        if (StringUtils.equals("/",requestUri)) {
            return true;
        }
        //放行exceptUrls中配置的url
        for (String url:excludeUrl) {
            if(url.endsWith("/**")){
                if (requestUri.startsWith(url.substring(0, url.length() - 3))) {
                    return true;
                }
            } else if (requestUri.startsWith(url)) {
                return true;
            }
        }
        //其他需要登录后才能进行访问的url
        if(httpServletRequest.getSession().getAttribute("userName") == null){
            httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest,httpServletResponse);
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
