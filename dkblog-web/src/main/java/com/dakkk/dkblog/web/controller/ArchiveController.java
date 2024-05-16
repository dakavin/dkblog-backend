package com.dakkk.dkblog.web.controller;

import com.dakkk.dkblog.common.aspect.ApiOperationLog;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.model.vo.archive.FindArchiveArticlePageListReqVO;
import com.dakkk.dkblog.web.service.ArchiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName: ArchiveController
 * Package: com.dakkk.dkblog.web.controller
 *
 * @Create 2024/5/16 11:57
 * @Author dakkk
 * Description:
 */
@RestController
@RequestMapping("/archive")
@Api(tags="前台 文章归档模块")
public class ArchiveController {
    @Resource
    private ArchiveService archiveService;

    @PostMapping("/list")
    @ApiOperation(value = "1-获取文章归档分页数据")
    @ApiOperationLog(description = "获取文章归档分页数据")
    public Response findArchivePageList(@RequestBody FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO){
        return archiveService.findArchivePageList(findArchiveArticlePageListReqVO);
    }
}
