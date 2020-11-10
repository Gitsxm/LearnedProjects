package com.example.demo_aop.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WebLogAspect
 * @Description 请求日志处理切面
 * @Author MGG
 * @Date 2020/3/23 10:01
 * @Version 1.0
 */
@Aspect
@Component
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.example.demo_aop.controller.*.*(..))")
    public void StuinfoController() {

    }

    @Before("StuinfoController()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("doBefore");
        //获取 Request 上下文
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //从request中获取http请求的url/请求的方法类型／响应该http请求的类方法／IP地址／请求中的参数
        //url
        logger.info("url={}", request.getRequestURI());
        //method
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() +
                "." + joinPoint.getSignature().getName());
        //参数
        logger.info("args={}", joinPoint.getArgs());
    }



    /**
     * 返回后结果日志
     *
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "StuinfoController()")
    public void doAfterReturning(Object object) {
        logger.info("Object={}", object);
    }
}
