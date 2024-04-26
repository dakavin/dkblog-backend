package com.dakkk.dkblog.admin.service.impl;

import com.dakkk.dkblog.admin.model.vo.user.FindUserInfoRspVO;
import com.dakkk.dkblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.dakkk.dkblog.admin.service.AdminUserService;
import com.dakkk.dkblog.common.domain.mapper.UserMapper;
import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.utils.Response;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName: AdminUserServiceImpl
 * Package: com.dakkk.dkblog.admin.service.impl
 *
 * @Create 2024/4/26 22:26
 * @Author dakkk
 * Description: 处理用户相关的业务代码
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
        // 拿到用户名和密码
        String username = updateAdminUserPasswordReqVO.getUsername();
        String password = updateAdminUserPasswordReqVO.getPassword();

        // 加密密码
        String encodePassword = passwordEncoder.encode(password);

        // 更新到数据库
        int count = userMapper.updatePasswordByUsernameInt(username, encodePassword);

        return count == 1 ? Response.success():Response.fail(ResponseErrorCodeEnum.USERNAME_NOT_FOUND);
    }

    @Override
    public Response findUserInfo() {
        // 获取存储在 ThreadLocal 中的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 拿到用户名
        String username = authentication.getName();

        return Response.success(FindUserInfoRspVO.builder().username(username).build());
    }
}
