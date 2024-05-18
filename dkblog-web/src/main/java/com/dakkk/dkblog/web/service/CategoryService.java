package com.dakkk.dkblog.web.service;

import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.model.vo.category.FindCategoryArticlePageListReqVO;

/**
 * ClassName: CategoryService
 * Package: com.dakkk.dkblog.web.service
 *
 * @Create 2024/5/10 16:39
 * @Author dakkk
 * Description:
 */
public interface CategoryService {
    /**
     * 获取分类列表
     */
    Response findCategoryList();

    /**
     *  获取分类下的文章分页数据
     */
    Response findCategoryArticlePageList(FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO);
}
