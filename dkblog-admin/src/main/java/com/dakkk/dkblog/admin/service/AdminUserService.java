package com.dakkk.dkblog.admin.service;

import com.dakkk.dkblog.admin.model.vo.UpdateAdminUserPasswordReqVO;
import com.dakkk.dkblog.common.utils.Response;

/**
 * ClassName: AdminUserService
 * Package: com.dakkk.dkblog.admin.service
 *
 * @Create 2024/4/26 22:26
 * @Author dakkk
 * Description: 处理用户相关的业务代码
 */
public interface AdminUserService {
    /**
     * 修改密码
     */
    Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);
}
