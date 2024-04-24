package com.dakkk.dkblog.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * ClassName: PasswordEncoderConfig
 * Package: com.dakkk.dkblog.jwt.config
 *
 * @Create 2024/4/22 17:58
 * @Author dakkk
 * Description:
 */
@Component
public class PasswordEncoderConfig {
    @Bean
    PasswordEncoder passwordEncoder(){
        // BCrypt 是一种安全且适合密码存储的哈希算法，它在进行哈希时会自动加入“盐”，增加密码的安全性。
        return new BCryptPasswordEncoder();
    }

    /**
     * 测试密码加盐
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("XXX"));
    }
}
