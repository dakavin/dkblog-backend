package com.dakkk.dkblog.admin.controller;

import com.dakkk.dkblog.admin.model.vo.category.AddCategoryReqVO;
import com.dakkk.dkblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.dakkk.dkblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.dakkk.dkblog.admin.service.AdminCategoryService;
import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import com.dakkk.dkblog.common.utils.PageResponse;
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
 * ClassName: AdminCategoryController
 * Package: com.dakkk.dkblog.admin.controller
 *
 * @Create 2024/4/27 14:23
 * @Author dakkk
 * Description:
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 分类模块")
public class AdminCategoryController {
    @Resource
    private AdminCategoryService categoryService;

    @PostMapping("/category/add")
    @ApiOperation("1-添加分类")
    @ApiOperationLog(description = "添加分类")
    public Response addCategory(@RequestBody @Validated AddCategoryReqVO addCategoryReqVO) {
        return categoryService.addCategory(addCategoryReqVO);
    }

    @PostMapping("/category/list")
    @ApiOperation("2-获取分类的分页数据")
    @ApiOperationLog(description = "获取分类的分页数据")
    public PageResponse findCategoryList(@RequestBody @Validated FindCategoryPageListReqVO findCategoryPageListReqVO){
        System.out.println(findCategoryPageListReqVO);
        return categoryService.findCategoryList(findCategoryPageListReqVO);
    }

    @PostMapping("/category/delete")
    @ApiOperation("3-删除某个id对应的分类")
    @ApiOperationLog(description = "删除某个id对应的分类")
    public Response deleteCategory(@RequestBody @Validated DeleteCategoryReqVO deleteCategoryReqVO){
        return categoryService.deleteCategory(deleteCategoryReqVO);
    }
}
