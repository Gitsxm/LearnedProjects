package com.example.springbootsecurity.config;

import com.example.springbootsecurity.filter.CustomAuthenticationFilter;
import com.example.springbootsecurity.filter.JWTAuthenticationFilter;
import com.example.springbootsecurity.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
     * Json 登录设置
     * @return
     * @throws Exception
     */
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(super.authenticationManager());
        authenticationFilter.setFilterProcessesUrl("/jsonlogin");
        return authenticationFilter;
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
                .antMatchers("/img/**", "/checkcode", "/js/**", "/css/**","/jsonlogin").permitAll()
                .antMatchers(settings.getPermitAll().split(",")).permitAll()
                //除上面两行所有请求都需要验证
                .anyRequest().authenticated()
                //登录处理
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .permitAll()
                //登录成功跳转
                .and().authorizeRequests()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                //鉴权异常处理
                .exceptionHandling()
                //未登录返回json信息 异常自定义处理 不再跳转
                .authenticationEntryPoint(new Http401AuthenticationEntryPoint())
                .accessDeniedPage(settings.getDeniedPage())
                .and().csrf()
                //用 api 访问时这个要禁用
                .disable()
                .addFilterBefore(new CustomAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(authenticationManager()),UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 权限过滤器
     *
     * @return
     */
    @Bean
    public CustomFilterSecurityInterceptor customFilterSecurityInterceptor() throws Exception {
        CustomFilterSecurityInterceptor interceptor = new CustomFilterSecurityInterceptor();
        interceptor.setSecurityMetadataSource(customSecurityMetadataSource());
        interceptor.setAccessDecisionManager(accessDecisionManager());
        interceptor.setAuthenticationManager(authenticationManager());
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
