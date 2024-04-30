package com.dakkk.dkblog.admin.controller;

import com.dakkk.dkblog.admin.model.vo.article.PublishArticleReqVO;
import com.dakkk.dkblog.admin.service.AdminArticleService;
import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import com.dakkk.dkblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: AdminArticleController
 * Package: com.dakkk.dkblog.admin.controller
 *
 * @Create 2024/4/30 15:38
 * @Author dakkk
 * Description:
 */
@RestController
@RequestMapping("article")
@Api(tags="Admin 文章模块")
public class AdminArticleController {
    @Resource
    AdminArticleService adminArticleService;

    @PostMapping("/publish")
    @ApiOperation("1-文章发布")
    @ApiOperationLog(description = "文章发布")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response publishArticle(@RequestBody @Validated PublishArticleReqVO publishArticleReqVO){
        return adminArticleService.publishArticle(publishArticleReqVO);
    }
}
