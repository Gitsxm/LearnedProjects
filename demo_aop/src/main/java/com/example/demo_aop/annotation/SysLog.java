package com.example.demo_aop.annotation;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/10 17:17
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SysLog {
    String value() default "";
    String module() default "";
}
