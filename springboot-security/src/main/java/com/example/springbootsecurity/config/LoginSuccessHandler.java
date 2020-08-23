package com.example.springbootsecurity.config;

import com.example.springbootsecurity.system.authorities.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理器(暂时没用)
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SysUser userDeatils = (SysUser) authentication.getPrincipal();
        //简单日志存储
        logger.info("登录用户:" + userDeatils.getUsername() + ";login:" + httpServletRequest.getContextPath());
        logger.info("IP:" + httpServletRequest.getRemoteAddr());
        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
    }
}
