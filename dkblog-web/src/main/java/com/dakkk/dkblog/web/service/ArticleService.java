package com.dakkk.dkblog.web.service;

import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.model.vo.article.FindArticleDetailReqVO;
import com.dakkk.dkblog.web.model.vo.article.FindIndexArticlePageListReqVO;

import javax.annotation.Resource;

/**
 * ClassName: ArticleService
 * Package: com.dakkk.dkblog.web.service
 *
 * @Create 2024/5/10 10:41
 * @Author dakkk
 * Description:
 */
public interface ArticleService {
    /**
     * 获取首页文章分页数据
     */
    Response findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO);

    /**
     * 获取文章详情
     */
    Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO);
}
