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
    PRODUCT_NOT_FOUND("20000","\uD83E\uDD2D该业务不存在哈。。。（测试使用）");


    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMsg;
}
