package com.predicated.blog.service.impl;

import com.predicated.blog.entity.Log;
import com.predicated.blog.repository.LogRepository;
import com.predicated.blog.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * 日志服务
 * @author Ray
 * @date created in 2019/11/15 22:06
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProceedingJoinPoint joinPoint, Log logInfo) {

        // 获取方法签名
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();

        // 获取方法上Log注解的值
        com.predicated.blog.annotation.Log aopLog = method.getAnnotation(com.predicated.blog.annotation.Log.class);
        if (aopLog != null) {
            logInfo.setDescription(aopLog.value());
        }

        // 设置方法名
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        logInfo.setClassMethod(methodName);

        // 设置方法参数
        StringBuilder sb = new StringBuilder("{");
        String[] argNames = signature.getParameterNames();
        Object[] argValues = joinPoint.getArgs();
        for (int i = 0; i < argNames.length; i++) {
            sb.append(argNames[i]).append(":").append(argValues[i]);
            if (i != argNames.length -1) {
                sb.append(" ");
            }
        }
        sb.append("}");
        logInfo.setParams(sb.toString());

        log.info("Request:{}", logInfo);
        logRepository.save(logInfo);
    }

}
