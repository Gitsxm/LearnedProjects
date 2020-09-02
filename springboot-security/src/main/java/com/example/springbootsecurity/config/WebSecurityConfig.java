package com.example.springbootsecurity.config;

import com.example.springbootsecurity.service.CustomUserService;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecuritySettings settings;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Resource(name = "customLogoutSuccessHandler")
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private DataSource dataSource;

    /**
     * 指定保存用户 token 数据源
     *
     * @return
     */
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }

    /**
     * 用户验证配置
     *
     * @return
     */
    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    /**
     * csrf 攻击拦截
     *
     * @return
     */
    @Bean
    protected CsrfSecurityRequestMatcher csrfSecurityRequestMatcher() {
        CsrfSecurityRequestMatcher csrfSecurityRequestMatcher = new CsrfSecurityRequestMatcher();
        List<String> list = new ArrayList<>();
        list.add("/rest");
        csrfSecurityRequestMatcher.setExcludeUrls(list);
        return csrfSecurityRequestMatcher;
    }

    /**
     * 密码解密方式
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new SrcPasswordEncoder());
    }

    /**
     * 安全设置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //拦截处理
        http.authorizeRequests()
                //允许不需要验证路径设置
                .antMatchers("/img/**", "/checkcode", "/js/**", "/css/**").permitAll()
                .antMatchers(settings.getPermitAll().split(",")).permitAll()
                //除上面两行所有请求都需要验证
                .anyRequest().authenticated()
                //登录处理
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .permitAll()
                //登录成功跳转
//                .successForwardUrl("/home")
                //登录成功处理
                .successHandler(loginSuccessHandler)
                //登录失败处理
                .failureHandler(loginFailureHandler)
                .and().authorizeRequests()

                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                //注销成功跳转
//                .logoutSuccessUrl(settings.getLogoutSuccessUrl())
                .and()
                //鉴权异常处理
                .exceptionHandling()
                //未登录返回json信息 异常自定义处理 不再跳转
                .authenticationEntryPoint((req, resp, authException) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("尚未登录，请先登录");
                    out.flush();
                    out.close();
                })
                //未登录访问跳转
                //.accessDeniedPage(settings.getDeniedPage())
                .and().rememberMe().tokenValiditySeconds(1800).tokenRepository(tokenRepository())
                .and().csrf()
                //用 api 访问时这个要禁用
                .disable();
                //.requireCsrfProtectionMatcher(csrfSecurityRequestMatcher());
    }

    /**
     * 权限过滤器
     *
     * @return
     */
    @Bean
    public CustomFilterSecurityInterceptor customFilterSecurityInterceptor() {
        CustomFilterSecurityInterceptor interceptor = new CustomFilterSecurityInterceptor();
        interceptor.setSecurityMetadataSource(customSecurityMetadataSource());
        interceptor.setAccessDecisionManager(accessDecisionManager());
//        interceptor.setAuthenticationManager();
        return interceptor;
    }

    /**
     * 权限资源管理器
     *
     * @return
     */
    @Bean
    public CustomSecurityMetadataSource customSecurityMetadataSource() {
        return new CustomSecurityMetadataSource(settings.getUrlRoles());
    }

    /**
     * 权限决断器
     *
     * @return
     */
    @Bean
    public CustomAccessDecisionManager accessDecisionManager() {
        return new CustomAccessDecisionManager();
    }

    /**
     * 静态资源过滤-这里的路径可以不走 spring-security 忽略路径的推荐做法
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/img");
    }

    /**
     * 角色继承
     *
     * @return
     */
//    @Bean
    protected RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "admin > manager /n manager > user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
}
