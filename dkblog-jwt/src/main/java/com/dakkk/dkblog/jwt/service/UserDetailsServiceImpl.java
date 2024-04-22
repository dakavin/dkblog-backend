package com.dakkk.dkblog.jwt.service;

import com.dakkk.dkblog.common.domain.dos.UserDO;
import com.dakkk.dkblog.common.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

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
    @Resource
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名从数据库查询用户
        UserDO userDO = userMapper.findByUsernameUserDo(username);
        // 判断用户是否存在
        if (Objects.isNull(userDO)){
            throw new UsernameNotFoundException("该用户不存在");
        }

        // authorities 用户指定角色，这里写死，将用户的角色设为 ADMIN 管理员
        return User.withUsername(userDO.getUsername())
                .password(userDO.getPassword())
                .authorities("ADMIN")
                .build();
    }
}
