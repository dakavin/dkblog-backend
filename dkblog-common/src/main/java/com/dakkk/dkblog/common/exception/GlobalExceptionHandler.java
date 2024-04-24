package com.dakkk.dkblog.common.exception;

import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import java.util.Optional;

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

    /**
     * 捕获参数校验不通过的异常：MethodArgumentNotValidException
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Response<Object> handleMethodArgumentNotValidException(HttpServletRequest req,MethodArgumentNotValidException e){
        // 参数错误的异常码
        String errorCode = ResponseErrorCodeEnum.PARAM_NOT_VALID.getErrorCode();
        // 获取 BindingResult
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder sb = new StringBuilder();
        // 获取校验不通过的字段，并组合为一个字符串
        Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent(errors ->{
            errors.forEach(error->{
                sb.append(error.getField())
                        .append(" ")
                        .append(error.getDefaultMessage())
                        .append(", 当前值：")
                        .append(error.getRejectedValue())
                        .append(";  ");
            });
        });

        // 最后错误的信息为
        String errorMsg = sb.toString();

        log.warn("{} request error , errorCode: {}, errorMessage: {}",req.getRequestURI(),errorCode,errorMsg);

        return Response.fail(errorCode,errorMsg);
    }

    // 捕获鉴权不通过的异常
    @ExceptionHandler({AccessDeniedException.class})
    public void throwAccessDeniedException(AccessDeniedException e) throws AccessDeniedException{
        // 捕获到鉴权失败异常，主动抛出，交给 RestAccessDeniedHandler处理
        log.info("==== 捕获到AccessDeniedException ====");
        throw e;
    }
}
