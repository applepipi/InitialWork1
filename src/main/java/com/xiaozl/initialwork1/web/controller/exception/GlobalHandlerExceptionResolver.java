package com.xiaozl.initialwork1.web.controller.exception;

import com.xiaozl.initialwork1.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 2017/12/13.
 */
public class GlobalHandlerExceptionResolver extends SimpleMappingExceptionResolver{
    private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);
    /**
     * 处理所有的异常信息
     */
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object o, Exception ex) {
        ex.printStackTrace();
        logger.error(ex.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        if (ex instanceof BusinessException) {
            //BusinessException自定义业务异常
            modelAndView.addObject("errorMessage",ex.getMessage());
            modelAndView.setViewName("error/500");
        }else if(ex instanceof MaxUploadSizeExceededException){
            //MaxUploadSizeExceededException上传文件异常
            modelAndView.setViewName("error/upload_error");
        }else{
            modelAndView.addObject("errorMessage","UnknownException");
            modelAndView.setViewName("error/500");
        }
        return modelAndView;
    }
}
