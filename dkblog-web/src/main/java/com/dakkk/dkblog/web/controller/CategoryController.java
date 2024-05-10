package com.dakkk.dkblog.web.controller;

import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/category")
@Api(tags = "前台 分类模块")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping("/list")
    @ApiOperation("1-前台获取分类列表")
    @ApiOperationLog(description = "前台获取分类列表")
    public Response findCategoryList(){
        return categoryService.findCategoryList();
    }
}
