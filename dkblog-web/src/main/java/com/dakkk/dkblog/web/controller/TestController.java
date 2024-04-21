package com.dakkk.dkblog.web.controller;

import com.dakkk.dkblog.web.model.User;
import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/test")
    @ApiOperationLog(description = "测试日志切面的接口")
    public User test(@RequestBody User user){
        return user;
    }
}
