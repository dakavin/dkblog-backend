package com.dakkk.dkblog.web.controller;

import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.model.vo.article.FindIndexArticlePageListReqVO;
import com.dakkk.dkblog.web.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: ArticleController
 * Package: com.dakkk.dkblog.web.controller
 *
 * @Create 2024/5/10 15:54
 * @Author dakkk
 * Description:
 */
@RestController
@RequestMapping("/article")
@Api(tags = "前台 文章模块")
public class ArticleController {
    @Resource
    ArticleService articleService;

    @PostMapping("/list")
    @ApiOperation("1-获取首页文章分页数据")
    @ApiOperationLog(description = "获取首页文章分页数据")
    public Response findArticlePageList(@RequestBody FindIndexArticlePageListReqVO findIndexArticlePageListReqVO){
        return articleService.findArticlePageList(findIndexArticlePageListReqVO);
    }
}
