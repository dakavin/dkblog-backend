package com.dakkk.dkblog.common.utils;

import com.dakkk.dkblog.common.exception.BaseExceptionInterface;
import com.dakkk.dkblog.common.exception.BizException;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: Response
 * Package: com.dakkk.dkblog.common.utils
 *
 * @Create 2024/4/21 20:51
 * @Author dakkk
 * Description:
 */
@Data
public class Response<T> implements Serializable {
    // 该类的序列化ID
    private static final long serialVersionUID = 3732417417154092568L;
    // 响应的状态
    private Boolean success = true;
    // 响应消息，
    private String msg;
    // 响应异常的异常码
    private String erroerCode;
    // 响应数据
    private T data;

    /**
     * 响应成功的方法1：没有成功返回的数据
     */
    public static <T> Response<T> success(){
        Response<T> response = new Response<>();
        return response;
    }

    /**
     * 响应成功的方法2：有成功返回的数据
     */
    public static <T> Response<T> success(T data){
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }

    /**
     * 响应失败的方法1：只有失败信息
     */
    public static <T> Response<T> fail(String errorMsg){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMsg(errorMsg);
        return response;
    }

    /**
     * 响应失败的方法2：响应失败的信息和失败状态码
     */
    public static <T> Response<T> fail(String erroerCode,String errorMsg){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErroerCode(erroerCode);
        response.setMsg(errorMsg);
        return response;
    }

    /**
     * 响应失败的方法3：通过bizException获取失败异常码和异常信息
     */
    public static <T> Response<T> fail(BizException e){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErroerCode(e.getErrorCode());
        response.setMsg(e.getErrorMsg());
        return response;
    }

    /**
     * 响应失败的方法4：通过BaseExceptionInterface的实现类，获取异常码和异常信息
     */
    public static <T> Response<T> fail(BaseExceptionInterface baseExceptionInterface){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErroerCode(baseExceptionInterface.getErrorCode());
        response.setMsg(baseExceptionInterface.getErrorMsg());
        return response;
    }


}
