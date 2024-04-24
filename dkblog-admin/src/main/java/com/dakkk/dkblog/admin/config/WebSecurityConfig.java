package com.dakkk.dkblog.admin.config;

import com.dakkk.dkblog.jwt.config.JwtAuthenticationSecurityConfig;
import com.dakkk.dkblog.jwt.filter.TokenAuthenticationFilter;
import com.dakkk.dkblog.jwt.handler.RestAccessDeniedHandler;
import com.dakkk.dkblog.jwt.handler.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * ClassName: WebSecurityConfig
 * Package: com.dakkk.dkblog.admin.config
 *
 * @Create 2024/4/22 16:20
 * @Author dakkk
 * Description:
 */
@Configuration
@EnableWebSecurity
// 启用方法级别的安全性设置，即在方法可以使用注解来定义访问控制规则
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private JwtAuthenticationSecurityConfig jwtAuthenticaitonSecurityConfig;
    @Resource
    private RestAuthenticationEntryPoint authEntryPoint;
    @Resource
    private RestAccessDeniedHandler deniedHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用 csrf
        http.csrf().disable()
                // 禁用表单登录,使用 JWT 登录不需要表单登录
                .formLogin().disable()
                // 应用jwt模块中自定义的用户认证配置
                .apply(jwtAuthenticaitonSecurityConfig)
                .and()
                // 后续是配置 HTTP 请求的认证规则
                .authorizeHttpRequests()
                // 认证所有以 /admin 为前缀的 URL资源
                .mvcMatchers("/admin/**").authenticated()
                // 其他都需要放行，无需认证
                .anyRequest().permitAll()
                .and()
                // 处理用户未登录访问受保护的资源的情况
                .httpBasic().authenticationEntryPoint(authEntryPoint)
                .and()
                // 处理登录成功后访问受保护的资源，但是权限不够的情况
                .exceptionHandling().accessDeniedHandler(deniedHandler)
                .and()
                // 前后端分离，设置会话管理策略为无状态的,即无需创建会话
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 将 Token 校验过滤器添加到用户认证过滤器之前
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * token 校验过滤器
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter(){
        return new TokenAuthenticationFilter();
    }
}
