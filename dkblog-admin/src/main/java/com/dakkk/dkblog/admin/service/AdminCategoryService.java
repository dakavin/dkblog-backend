package com.dakkk.dkblog.admin.service;

import com.dakkk.dkblog.admin.model.vo.category.AddCategoryReqVO;
import com.dakkk.dkblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.dakkk.dkblog.admin.model.vo.category.FindCategoryPageListReqVO;
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
public interface AdminCategoryService {
    /**
     * 添加分类
     */
    Response addCategory(AddCategoryReqVO addCategoryReqVO);
    /**
     * 分类的分页数据查询
     */
    PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO);

    /**
     * 删除分类
     */
    Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO);
    /**
     * 获取文章分类的 Select 列表数据
     */
    Response findCategorySelectList();
}
