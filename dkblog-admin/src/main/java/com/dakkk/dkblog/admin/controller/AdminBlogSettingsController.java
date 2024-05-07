package com.dakkk.dkblog.admin.controller;

import com.dakkk.dkblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.dakkk.dkblog.admin.service.AdminBlogSettingsService;
import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import com.dakkk.dkblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: AdminBlogSettingsController
 * Package: com.dakkk.dkblog.admin.controller
 *
 * @Create 2024/5/7 17:49
 * @Author dakkk
 * Description:
 */
@RestController
@RequestMapping("/admin/blog/settings")
@Api(tags = "Admin 博客设置模块")
public class AdminBlogSettingsController {
    @Resource
    AdminBlogSettingsService adminBlogSettingsService;

    @PostMapping("/update")
    @ApiOperation("1-博客设置更新")
    @ApiOperationLog(description = "博客设置更新")
    public Response updateBlogSettings(@RequestBody @Validated UpdateBlogSettingsReqVO
                                       updateBlogSettingsReqVO){
        return adminBlogSettingsService.updateBlogSettings(updateBlogSettingsReqVO);
    }
}
