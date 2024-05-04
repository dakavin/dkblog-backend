package com.dakkk.dkblog.admin.controller;

import com.dakkk.dkblog.admin.model.vo.category.UpdateCategoryReqVO;
import com.dakkk.dkblog.admin.model.vo.tag.AddTagReqVO;
import com.dakkk.dkblog.admin.model.vo.tag.DeleteTagReqVO;
import com.dakkk.dkblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.dakkk.dkblog.admin.model.vo.tag.UpdateTagReqVO;
import com.dakkk.dkblog.admin.service.AdminTagService;
import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import com.dakkk.dkblog.common.utils.PageResponse;
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
 * ClassName: AdminCategoryController
 * Package: com.dakkk.dkblog.admin.controller
 *
 * @Create 2024/4/27 14:23
 * @Author dakkk
 * Description:
 */
@RestController
@RequestMapping("/admin/tag")
@Api(tags = "Admin 标签模块")
public class AdminTagController {
    @Resource
    private AdminTagService tagService;

    @PostMapping("/add")
    @ApiOperation("1-添加标签")
    @ApiOperationLog(description = "添加标签")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response addTag(@RequestBody @Validated AddTagReqVO addTagReqVO) {
        return tagService.addTag(addTagReqVO);
    }

    @PostMapping("/list")
    @ApiOperation("2-获取标签的分页数据")
    @ApiOperationLog(description = "获取标签的分页数据")
    public PageResponse findTagList(@RequestBody @Validated FindTagPageListReqVO findTagPageListReqVO){
        System.out.println(findTagPageListReqVO);
        return tagService.findTagList(findTagPageListReqVO);
    }

    @PostMapping("/delete")
    @ApiOperation("3-删除某个id对应的标签")
    @ApiOperationLog(description = "删除某个id对应的标签")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response deleteTag(@RequestBody @Validated DeleteTagReqVO deleteTagReqVO){
        return tagService.deleteTag(deleteTagReqVO);
    }

    @PostMapping("/select/list")
    @ApiOperation("4-创建文章时，获取分类下拉列表中的数据")
    @ApiOperationLog(description = "创建文章时，获取分类下拉列表中的数据")
    public Response findTagSelectList(){
        return tagService.findTagSelectList();
    }

    @PostMapping("/update")
    @ApiOperation("5-修改分类")
    @ApiOperationLog(description = "修改分类")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response updateTag(@RequestBody @Validated UpdateTagReqVO updateTagReqVO) {
        return tagService.updateTag(updateTagReqVO);
    }
}
