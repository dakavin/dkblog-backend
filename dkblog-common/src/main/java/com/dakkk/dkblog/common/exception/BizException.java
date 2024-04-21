package com.dakkk.dkblog.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: BizException
 * Package: com.dakkk.dkblog.common.exception
 *
 * @Create 2024/4/21 21:10
 * @Author dakkk
 * Description:
 */
@Getter
@Setter
public class BizException extends RuntimeException{
    // 异常码
    private String errorCode;
    // 异常信息
    private String errorMsg;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMsg = baseExceptionInterface.getErrorMsg();
    }
}
