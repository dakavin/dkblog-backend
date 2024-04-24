package com.dakkk.dkblog.jwt.service;

import com.dakkk.dkblog.common.domain.dos.UserDO;
import com.dakkk.dkblog.common.domain.dos.UserRoleDO;
import com.dakkk.dkblog.common.domain.mapper.UserMapper;
import com.dakkk.dkblog.common.domain.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Resource
    UserRoleMapper userRoleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名从数据库查询用户
        UserDO userDO = userMapper.findByUsernameUserDo(username);
        // 判断用户是否存在
        if (Objects.isNull(userDO)){
            throw new UsernameNotFoundException("该用户不存在");
        }

        // 从数据库中获取用户角色
        List<UserRoleDO> roleDOS = userRoleMapper.selectByUsername(username);

        // 构建一个数组，用来获取用户的role属性
        String[] roleArr = null;

        // 获取roleDOS 中的 role属性
        if (!CollectionUtils.isEmpty(roleDOS)){
            List<String> roles = roleDOS.stream().map(p -> p.getRole()).collect(Collectors.toList());
            roleArr = roles.toArray(new String[roles.size()]);
        }

        // authorities 指定用户的角色，用户的角色通过roleArr数组获取
        return User.withUsername(userDO.getUsername())
                .password(userDO.getPassword())
                .authorities(roleArr)
                .build();
    }
}
