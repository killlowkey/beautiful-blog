package com.predicated.blog.service;

import com.predicated.blog.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author Ray
 * @date created in 2019/11/15 22:06
 */
public interface LogService {
    /**
     * @param joinPoint 日志切面点
     * @param log       Log对象
     */
    void save(ProceedingJoinPoint joinPoint, Log log);
}
