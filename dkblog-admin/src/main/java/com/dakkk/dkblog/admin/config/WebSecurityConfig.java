package com.dakkk.dkblog.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                // 认证所有以 /admin 为前缀的 URL 资源
                .mvcMatchers("/admin/**").authenticated()
                // 其他都需要放行，无需认证
                .anyRequest().permitAll().and()
                // 使用表单登录
                .formLogin().and()
                // 使用 HTTP Basic 认证
                .httpBasic();
    }
}
