package com.dakkk.dkblog.admin.controller;

import com.dakkk.dkblog.admin.service.AdminFileService;
import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import com.dakkk.dkblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * ClassName: AdminFileController
 * Package: com.dakkk.dkblog.admin.controller
 *
 * @Create 2024/5/7 17:08
 * @Author dakkk
 * Description:
 */
@RestController
@RequestMapping("/admin/file")
@Api(tags = "Admin 文件模块")
public class AdminFileController {
    @Resource
    AdminFileService adminFileService;

    @PostMapping("/upload")
    @ApiOperation("1-文件上传接口")
    @ApiOperationLog(description = "文件上传接口")
    public Response uploadFile(@RequestParam MultipartFile file){
        return adminFileService.uploadFile(file);
    }
}
