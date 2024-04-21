package com.dakkk.dkblog.common.exception;

/**
 * ClassName: BaseExceptionInterface
 * Package: com.dakkk.dkblog.common.exception
 *
 * @Create 2024/4/21 21:04
 * @Author dakkk
 * Description:
 */
public interface BaseExceptionInterface {
    /**
     * 获取业务异常码
     * @return 业务异常码
     */
    String getErrorCode();

    /**
     * 获取业务异常信息
     * @return 业务异常信息
     */
    String getErrorMsg();
}
