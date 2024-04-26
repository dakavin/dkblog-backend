package com.dakkk.dkblog.admin.model.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: FindUserInfoRspVO
 * Package: com.dakkk.dkblog.admin.model.vo.user
 *
 * @Create 2024/4/26 22:52
 * @Author dakkk
 * Description: 前端获取当前登录用户的 返参
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserInfoRspVO {
    /**
     * 用户名
     */
    private String username;
}
