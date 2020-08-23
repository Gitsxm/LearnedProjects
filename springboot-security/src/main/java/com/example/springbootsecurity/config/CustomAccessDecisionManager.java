package com.example.springbootsecurity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * 权限管理决断器
 */
public class CustomAccessDecisionManager implements AccessDecisionManager {
    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if (null == collection)
            return;
        Iterator<ConfigAttribute> attributeIterator = collection.iterator();
        while (attributeIterator.hasNext()){
            ConfigAttribute config = attributeIterator.next();
            //need role
            String needRole = config.getAttribute();
            //user role
            for (GrantedAuthority ga:authentication.getAuthorities()){
                if (needRole.equals(ga.getAuthority()))
                    return;
            }
            logger.info("need role is "+needRole);
        }
        throw new AccessDeniedException("Cannot access!");
//        throw new RuntimeException("Cannot access!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
