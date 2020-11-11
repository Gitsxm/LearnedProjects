package com.example.demo_aop.config;


import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

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

    /**
     * 后置通知
     *
     * @param joinPoint
     */
    @After("StuinfoController()")
    public void doAfter(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        logger.info("调用后连接点方法为：" + className +"."+ methodName + "(),参数为：" + args);
    }

    /**
     * 异常通知
     *
     * @param point
     * @param ex
     */
    @AfterThrowing(pointcut = "StuinfoController()", throwing = "ex")
    public void afterReturning(JoinPoint point, Exception ex) {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        logger.info("连接点方法为：" + className +"."+ methodName + "(),参数为：" + args + ",异常为：" + ex);
    }
}
