package com.dakkk.dkblog.common.enums;

import com.dakkk.dkblog.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: ErrorCodeEnum
 * Package: com.dakkk.dkblog.common.exception
 *
 * @Create 2024/4/21 21:06
 * @Author dakkk
 * Description:
 */
@Getter
@AllArgsConstructor
public enum ResponseErrorCodeEnum implements BaseExceptionInterface {
    // 通用异常状态码
    SYSTEM_ERROR("10000","\uD83D\uDE11出错啦，后台小哥正在努力修复中。。。"),
    // 业务异常状态码
    PRODUCT_NOT_FOUND("20000","\uD83E\uDD2D该业务不存在哈。。。（测试使用）"),
    // 参数异常状态码
    PARAM_NOT_VALID("10001","\uD83E\uDD28填写的参数错误哦"),
    // 用户认证过程中，登录失败
    LOGIN_FAIL("20000", "\uD83D\uDE35登录失败哟"),
    // 用户认证过程中，用户名和密码错误
    USERNAME_OR_PWD_ERROR("20001", "\uD83E\uDDD0用户名或密码错误啦"),
    // 用户未登录，访问受保护的资源
    UNAUTHORIZED("20002", "\uD83E\uDEE4无访问权限，请先登录呀！"),
    // 用户已登录，但是无权访问受保护的资源
    FORBIDDEN("20004", "\uD83D\uDE0E演示账号仅支持查询操作哈！");


    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMsg;
}
