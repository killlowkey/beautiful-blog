package com.predicated.blog.handler;

import com.predicated.blog.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 *
 * @author Ray
 * @date created in 2019/11/15 20:16
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    /**
     * 处理 {@link Exception} 异常以及它的子类
     *
     * @param request 用户请求
     * @param e       异常
     * @return 视图
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("Request URL:{} Exception: {}", request.getRequestURL(), e);

        ModelAndView mv = new ModelAndView();

        // 处理自定义的 CustomException
        if (e instanceof CustomException) {

        } else {
            mv.addObject("url", request.getRequestURL());
            mv.addObject("exception", e);
            mv.setViewName("error/error");
        }

        return mv;
    }

}
