package com.example.springbootsecurity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 排除 Csrf 保护策略
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {
    protected Logger logger = LoggerFactory.getLogger(CsrfSecurityRequestMatcher.class);
    private Pattern allowedMatchers = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
    //排除 url 列表
    private List<String> excludeUrls;

    /**
     * 对指定 url 排除防护
     * @param httpServletRequest
     * @return
     */
    @Override
    public boolean matches(HttpServletRequest httpServletRequest) {
        if (null != excludeUrls && 0 < excludeUrls.size()){
            String servletPath = httpServletRequest.getServletPath();
            for (String url: excludeUrls){
                if (servletPath.contains(url)){
                    logger.info("+++"+servletPath);
                    return false;
                }
            }
        }
        return !allowedMatchers.matcher(httpServletRequest.getMethod()).matches();
    }

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }
}
