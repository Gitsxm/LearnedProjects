package com.example.springbootsecurity.config;

import com.example.springbootsecurity.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecuritySettings settings;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

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
                //登录处理
                .and().formLogin().loginPage("/login").permitAll().successForwardUrl("/home")
                .and().authorizeRequests()
                //允许不需要验证路径设置
                .antMatchers("/images/**", "/checkcode", "/scripts/**", "/styles/**").permitAll()
                .antMatchers(settings.getPermitAll().split(",")).permitAll()
                //除上面两行所有请求都需要验证
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().logout().logoutSuccessUrl(settings.getLogoutSuccessUrl())
                .and().exceptionHandling().accessDeniedPage(settings.getDeniedPage())
                .and().rememberMe().tokenValiditySeconds(1800).tokenRepository(tokenRepository())
                .and().csrf().requireCsrfProtectionMatcher(csrfSecurityRequestMatcher());
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
     * 静态资源过滤
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/img");
    }
}
