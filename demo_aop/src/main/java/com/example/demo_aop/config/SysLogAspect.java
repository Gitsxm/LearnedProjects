package com.example.demo_aop.config;

import com.alibaba.fastjson.JSON;
import com.example.demo_aop.annotation.SysLog;
import com.example.demo_aop.domain.SysLogEntity;
import com.example.demo_aop.service.SysLogService;
import com.example.demo_aop.service.impl.SysLogServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 系统日志切面
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/10 17:21
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogService service;

    @Pointcut("@annotation(com.example.demo_aop.annotation.SysLog)")
    public void sysLogPointCut() {
        System.out.println("================================>");
    }

    @Around("sysLogPointCut()")
    public Object arround(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        saveSysLog(joinPoint, time);
        return joinPoint.proceed();
    }

    private void saveSysLog(JoinPoint joinPoint,long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogEntity logEntity = new SysLogEntity();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (null != sysLog) {
            logEntity.setModule(sysLog.module());
            logEntity.setOperation(sysLog.value());
        }
        //user
        logEntity.setUser("");
        //request methos
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        logEntity.setMethod(className + "." + methodName + "()");
        //get args
        Object[] args = joinPoint.getArgs();
        try {
            if (args.length>0){
                String params = JSON.toJSONString(args[0]);
                logEntity.setArgs(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //get request context
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logEntity.setIp(request.getRemoteAddr());
        //other fields
        logEntity.setCreatetime(new Date());
        logEntity.setTime(String.valueOf(time));
        service.insert(logEntity);
    }
}
