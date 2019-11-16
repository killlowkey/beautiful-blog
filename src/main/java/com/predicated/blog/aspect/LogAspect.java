package com.predicated.blog.aspect;

import com.predicated.blog.entity.Log;
import com.predicated.blog.service.LogService;
import com.predicated.blog.util.WebUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 * @author Ray
 * @date created in 2019/11/15 21:46
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.predicated.blog.annotation.Log)")
    public void log() {}

    @Around("log()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        Log log = new Log(WebUtil.getRequestIp(), end - start);
        logService.save(joinPoint, log);

        return result;
    }
}
