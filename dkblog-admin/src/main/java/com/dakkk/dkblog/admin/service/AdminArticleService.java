package com.dakkk.dkblog.admin.service;

import com.dakkk.dkblog.admin.model.vo.article.*;
import com.dakkk.dkblog.common.utils.Response;

/**
 * ClassName: AdminArticleService
 * Package: com.dakkk.dkblog.admin.service
 *
 * @Create 2024/4/30 15:23
 * @Author dakkk
 * Description:
 */
public interface AdminArticleService {
    /**
     * 发布文章
     */
    Response publishArticle(PublishArticleReqVO publishArticleReqVO);

    /**
     * 删除文章
     */
    Response deleteArticle(DeleteArticleReqVO deleteArticleReqVO);

    /**
     * 分页查询文章
     */
    Response findArticlePageList(FindArticlePageListReqVO findArticlePageListReqVO);

    /**
     * 查询文章详情接口
     */
    Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO);

    /**
     * 更新文章
     */
    Response updateArticle(UpdateArticleReqVO updateArticleReqVO);
}
