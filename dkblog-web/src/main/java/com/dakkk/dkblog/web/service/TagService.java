package com.dakkk.dkblog.web.service;

import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.model.vo.category.FindCategoryArticlePageListReqVO;
import com.dakkk.dkblog.web.model.vo.tag.FindTagArticlePageListReqVO;

/**
 * ClassName: CategoryService
 * Package: com.dakkk.dkblog.web.service
 *
 * @Create 2024/5/10 16:39
 * @Author dakkk
 * Description:
 */
public interface TagService {
    /**
     * 获取分类列表
     */
    Response findTagList();

    /**
     *  获取分类下的文章分页数据
     */
    Response findTagArticlePageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO);
}
