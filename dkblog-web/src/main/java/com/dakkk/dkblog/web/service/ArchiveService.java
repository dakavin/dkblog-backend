package com.dakkk.dkblog.web.service;

import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.model.vo.archive.FindArchiveArticlePageListReqVO;
import com.dakkk.dkblog.web.model.vo.archive.FindArchiveArticlePageListRspVO;

/**
 * ClassName: ArchiveService
 * Package: com.dakkk.dkblog.web.service
 *
 * @Create 2024/5/16 11:09
 * @Author dakkk
 * Description:
 */
public interface ArchiveService {
    /**
     * 获取文章归档的分页数据
     * @param findArchiveArticlePageListRspVO
     * @return
     */
    Response findArchivePageList(FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO);
}
