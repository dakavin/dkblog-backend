package com.dakkk.dkblog.web.controller;

import com.dakkk.dkblog.common.exception.BizException;
import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.utils.JsonUtil;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.model.User;
import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

/**
 * ClassName: TestController
 * Package: com.dakkk.blog.web.controller
 *
 * @Create 2024/4/21 20:10
 * @Author dakkk
 * Description:
 */
@RestController
@Slf4j
@Api(tags = "测试模块")
public class TestController {
    @PostMapping("/test1")
    @ApiOperationLog(description = "测试日志切面的接口")
    @ApiOperation("测试日志切面的接口")
    public User test1(@RequestBody User user){
        return user;
    }

    @PostMapping("/test2")
    @ApiOperationLog(description = "测试参数校验的接口")
    @ApiOperation("测试参数校验的接口")
    public ResponseEntity<String> test2(@RequestBody @Validated User user, BindingResult bindingResult){
        // 如果参数存在错误，则返回错误的参数
        if (bindingResult.hasErrors()){
            // 获取校验错误的每一个字段
            String errorMsg = bindingResult.getFieldErrors()
                    .stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(","));
            return ResponseEntity.badRequest().body(errorMsg);
        }
        // 没有错误
        return ResponseEntity.ok("参数没有问题");
    }

    @PostMapping("/test3")
    @ApiOperationLog(description = "测试全局响应的接口")
    @ApiOperation("测试全局响应的接口")
    public Response test3(@RequestBody @Validated User user, BindingResult bindingResult){
        // 如果参数存在错误，则返回错误的参数
        if (bindingResult.hasErrors()){
            // 获取校验错误的每一个字段
            String errorMsg = bindingResult.getFieldErrors()
                    .stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(","));
            return Response.fail(errorMsg);
        }
        // 没有错误
        return Response.success();
    }

    @PostMapping("/test4")
    @ApiOperationLog(description = "测试BizException异常响应的接口")
    @ApiOperation("测试BizException异常响应的接口")
    public Response test4(@RequestBody @Validated User user, BindingResult bindingResult){
        // 手动抛异常，入参是前面定义好的异常码枚举，返回统一交给全局处理器搞定
        throw new BizException(ResponseErrorCodeEnum.PRODUCT_NOT_FOUND);
    }

    @PostMapping("/test5")
    @ApiOperationLog(description = "测试Exception异常响应的接口")
    @ApiOperation("测试Exception异常响应的接口")
    public Response test5(@RequestBody @Validated User user, BindingResult bindingResult){
        // 手动抛异常，入参是前面定义好的异常码枚举，返回统一交给全局处理器搞定
        int i = 1/0;
        return Response.success();
    }

    @PostMapping("/test6")
    @ApiOperationLog(description = "测试参数异常响应的接口")
    @ApiOperation("测试参数异常响应的接口")
    public Response test6(@RequestBody @Validated User user){
        // 入参的时候，填写不符合标准的参数来测试
        System.out.println(user);
        return Response.success();
    }

    @PostMapping("/test7")
    @ApiOperationLog(description = "测试时间格式是否正确响应的接口")
    @ApiOperation("测试时间格式是否正确响应的接口")
    public Response test7(@RequestBody @Validated User user){
        // 设置三种日期字段值
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDate.now());
        user.setTime(LocalTime.now());
        return Response.success(user);
    }

    @PostMapping("/admin/test")
    @ApiOperationLog(description = "测试Security是否能拦截的接口")
    @ApiOperation("测试日志切面的接口")
    public Response testSecurity(@RequestBody @Validated User user){
        log.info(JsonUtil.toJsonString(user));

        return Response.success();
    }
}
