package com.dakkk.dkblog.admin.service;


import com.dakkk.dkblog.admin.model.vo.tag.AddTagReqVO;
import com.dakkk.dkblog.admin.model.vo.tag.DeleteTagReqVO;
import com.dakkk.dkblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.dakkk.dkblog.admin.model.vo.tag.UpdateTagReqVO;
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
     * 添加分类
     */
    Response addTag(AddTagReqVO addTagReqVO);
    /**
     * 分类的分页数据查询
     */
    PageResponse findTagList(FindTagPageListReqVO findTagPageListReqVO);

    /**
     * 删除分类
     */
    Response deleteTag(DeleteTagReqVO deleteTagReqVO);
    /**
     * 获取文章分类的 Select 列表数据
     */
    Response findTagSelectList();
    /**
     * 修改分类
     */
    Response updateTag(UpdateTagReqVO updateTagReqVO);
}
