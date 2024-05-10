package com.dakkk.dkblog.web.controller;

import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.service.BlogSettingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: BlogSettingsController
 * Package: com.dakkk.dkblog.web.controller
 *
 * @Create 2024/5/10 17:12
 * @Author dakkk
 * Description:
 */
@RestController
@RequestMapping("/blog/settings")
@Api(tags = "前台 博客设置信息模块")
public class BlogSettingsController {
    @Resource
    private BlogSettingsService blogSettingsService;

    @PostMapping("/detail")
    @ApiOperation("1-前台获取博客设置信息")
    @ApiOperationLog(description = "前台获取博客设置信息")
    public Response findDetail(){
        return blogSettingsService.findDetail();
    }
}
