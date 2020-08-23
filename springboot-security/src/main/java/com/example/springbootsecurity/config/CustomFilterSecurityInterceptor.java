package com.example.springbootsecurity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 权限管理过滤器
 */
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CustomFilterSecurityInterceptor.class);
    private FilterInvocationSecurityMetadataSource securityMetadataSource;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(servletRequest,servletResponse,filterChain);
        logger.debug("====="+fi.getRequestUrl());
        invoke(fi);
    }

    public void invoke(FilterInvocation fi){
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(),fi.getResponse());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (ServletException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }finally {
            super.afterInvocation(token,null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return securityMetadataSource;
    }

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return securityMetadataSource;
    }

    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }
}
