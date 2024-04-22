package com.dakkk.dkblog.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: LoginRspVO
 * Package: com.dakkk.dkblog.jwt.model
 *
 * @Create 2024/4/22 18:29
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRspVO {
    /**
     * 返回当前用户的令牌 token
     */
    private String token;
}
