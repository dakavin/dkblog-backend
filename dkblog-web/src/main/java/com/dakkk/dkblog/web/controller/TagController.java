package com.dakkk.dkblog.web.controller;

import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.model.vo.category.FindCategoryArticlePageListReqVO;
import com.dakkk.dkblog.web.model.vo.tag.FindTagArticlePageListReqVO;
import com.dakkk.dkblog.web.service.CategoryService;
import com.dakkk.dkblog.web.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: CategoryController
 * Package: com.dakkk.dkblog.web.controller
 *
 * @Create 2024/5/10 16:38
 * @Author dakkk
 * Description:
 */
@RestController
@RequestMapping("/tag")
@Api(tags = "前台 标签模块")
public class TagController {
    @Resource
    private TagService tagService;

    @PostMapping("/list")
    @ApiOperation("1-前台获取标签列表")
    @ApiOperationLog(description = "前台获取标签列表")
    public Response findCategoryList(){
        return tagService.findTagList();
    }

    @PostMapping("/article/list")
    @ApiOperation("2-前台获取标签对应的文章列表")
    @ApiOperationLog(description = "前台获取标签对应的文章列表")
    public Response findCategoryArticlePageList(@RequestBody @Validated
                                                FindTagArticlePageListReqVO findTagArticlePageListReqVO){
        return tagService.findTagArticlePageList(findTagArticlePageListReqVO);
    }
}
