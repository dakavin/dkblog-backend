package com.dakkk.dkblog.web.controller;

import com.dakkk.dkblog.web.model.User;
import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
public class TestController {
    @PostMapping("/test1")
    @ApiOperationLog(description = "测试日志切面的接口")
    public User test1(@RequestBody User user){
        return user;
    }

    @PostMapping("/test2")
    @ApiOperationLog(description = "测试参数校验的接口")
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
}
