package com.dakkk.dkblog.jwt.config;

import com.dakkk.dkblog.jwt.filter.JwtAuthenticationFilter;
import com.dakkk.dkblog.jwt.handler.RestAuthenticaitonFailureHandler;
import com.dakkk.dkblog.jwt.handler.RestAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * ClassName: JwtAuthenticationSecurityConfig
 * Package: com.dakkk.dkblog.common.config
 *
 * @Create 2024/4/22 18:46
 * @Author dakkk
 * Description:
 */
@Configuration
public class JwtAuthenticationSecurityConfig extends
        SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Resource
    private RestAuthenticationSuccessHandler restAuthenticaitonSuccessHandler;
    @Resource
    private RestAuthenticaitonFailureHandler restAuthenticaitonFailureHandler;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        // 自定义的用于 JWT 身份验证的过滤器
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();

        // 设置过滤器的认证管理员，用于处理身份验证请求
        filter.setAuthenticationManager(httpSecurity.getSharedObject(AuthenticationManager.class));

        // 设置登录认证对应的处理类
        filter.setAuthenticationSuccessHandler(restAuthenticaitonSuccessHandler);
        filter.setAuthenticationFailureHandler(restAuthenticaitonFailureHandler);

        // 直接使用 DaoAuthenticationProvider，它是 Spring Security 提供的 默认身份验证提供者 之一
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 将用户信息放入 身份提供者 中去
        provider.setUserDetailsService(userDetailsService);
        // 对用户密码设置加密算法
        provider.setPasswordEncoder(passwordEncoder);
        // 将身份验证提供者添加到 HTTP 安全策略中
        httpSecurity.authenticationProvider(provider);
        // 将这个jwt过滤器添加到 Spring Security 的过滤器链中
        // 在 原生UsernamePasswordAuthenticationFilter过滤器 之前执行
        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
