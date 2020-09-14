package com.example.springbootsecurity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.*;

/**
 * 权限配置资源管理器
 */
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static final Logger logger = LoggerFactory.getLogger(CustomSecurityMetadataSource.class);
    private Map<String, Collection<ConfigAttribute>> resourceMap = null;
    private PathMatcher pathMatcher = new AntPathMatcher();
    private String urlroles;

    public CustomSecurityMetadataSource(String urlroles) {
        super();
        this.urlroles = urlroles;
        resourceMap = loadResourceMatchAuthority();
    }

    /**
     * 获取资源与权限信息
     *
     * @return
     */
    private Map<String, Collection<ConfigAttribute>> loadResourceMatchAuthority() {
        Map<String, Collection<ConfigAttribute>> map = new HashMap<>();
        if (null != urlroles && !urlroles.isEmpty()) {
            String[] resources = urlroles.split(";");
            for (String resource : resources) {
                String[] urls = resource.split("=");
                String[] roles = urls[1].split(",");
                Collection<ConfigAttribute> list = new ArrayList<>();
                for (String role : roles) {
                    ConfigAttribute config = new SecurityConfig(role.trim());
                    list.add(config);
                }
                map.put(urls[0].trim(), list);
            }
        } else {
            logger.error("'securityconfig.urlroles' must be set!");
        }
        logger.info("Loaded UrlRoles Resource.");
        return map;
    }

    /**
     * 获取属性
     *
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String url = ((FilterInvocation) o).getRequestUrl();
        logger.debug("request url is " + url);
        if (resourceMap == null)
            resourceMap = loadResourceMatchAuthority();
        Iterator<String> iterator = resourceMap.keySet().iterator();
        while (iterator.hasNext()) {
            String resUrl = iterator.next();
            if (pathMatcher.match(resUrl, url)) {
                return resourceMap.get(resUrl);
            }
        }
        return resourceMap.get(url);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }


}
