package com.dakkk.dkblog.web.controller;

import com.dakkk.dkblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.dakkk.dkblog.admin.service.AdminUserService;
import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import com.dakkk.dkblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: AdminUserController
 * Package: com.dakkk.dkblog.web.controller
 *
 * @Create 2024/4/26 22:35
 * @Author dakkk
 * Description:
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin用户模块")
public class AdminUserController {
    @Resource
    private AdminUserService userService;

    @PostMapping("/password/update")
    @ApiOperation("1-修改用户密码")
    @ApiOperationLog(description = "修改用户密码")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response updatePassword(@RequestBody @Validated UpdateAdminUserPasswordReqVO
                                   updateAdminUserPasswordReqVO){
        return userService.updatePassword(updateAdminUserPasswordReqVO);
    }

    @PostMapping("/user/info")
    @ApiOperation("2-获取用户信息")
    @ApiOperationLog(description = "获取用户信息")
    public Response findUserInfo(){
        return userService.findUserInfo();
    }
}
