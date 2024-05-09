package com.dakkk.dkblog.admin.service;


import com.dakkk.dkblog.admin.model.vo.tag.*;
import com.dakkk.dkblog.common.utils.PageResponse;
import com.dakkk.dkblog.common.utils.Response;

/**
 * ClassName: AdminCategoryService
 * Package: com.dakkk.dkblog.admin.service
 *
 * @Create 2024/4/27 14:15
 * @Author dakkk
 * Description:
 */
public interface AdminTagService {
    /**
     * 添加标签
     */
    Response addTag(AddTagReqVO addTagReqVO);
    /**
     * 标签的分页数据查询
     */
    PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO);

    /**
     * 删除标签
     */
    Response deleteTag(DeleteTagReqVO deleteTagReqVO);
    /**
     * 根据标签关键词模糊查询
     */
    Response searchTags(SearchTagsReqVO searchTagsReqVO);
    /**
     * 修改分类
     */
    Response updateTag(UpdateTagReqVO updateTagReqVO);

    /**
     * 查询标签 Select 列表数据
     */
    Response findTagSelectList();
}
