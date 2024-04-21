package com.dakkk.dkblog.common.exception;

import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.dakkk.dkblog.common.exception
 *
 * @Create 2024/4/21 21:12
 * @Author dakkk
 * Description:
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获BizException异常，并返回该异常的信息
     */
    @ExceptionHandler({BizException.class})
    @ResponseBody
    public Response<Object> handleBizException(HttpServletRequest req,BizException e){
        log.warn("{} request fail,errorCode:{},errorMsg:{}",req.getRequestURI(),e.getErrorCode(),e.getErrorMsg());
        return Response.fail(e);
    }

    /**
     * 捕获其他异常，其他异常直接使用系统异常的异常码来返回
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Response<Object> handleOtherException(HttpServletRequest req,Exception e){
        log.warn("{} request error,exceptionMsg:{}",req.getRequestURI(),e.getMessage());
        return Response.fail(ResponseErrorCodeEnum.SYSTEM_ERROR);
    }

}
