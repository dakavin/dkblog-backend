package com.dakkk.dkblog.jwt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserDetailServiceImpl
 * Package: com.dakkk.dkblog.jwt.service
 *
 * @Create 2024/4/22 18:08
 * @Author dakkk
 * Description:
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // todo 从数据库查询并获取用户信息

        // 暂时先写死，用户为 zhangsan 密码使用加密过的Bcrypt密码
        // authorities 用户指定角色，这里写死，将用户的角色设为 ADMIN 管理员
        return User.withUsername("zhangsan")
                .password("$2a$10$j6i/1HDGI0r98SEYfNZmW.6bban7mwWxl6ANGWu09xkhDTsrFgP8C")
                .authorities("ADMIN")
                .build();
    }
}
