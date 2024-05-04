package com.dakkk.dkblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dakkk.dkblog.common.config.InsertBatchMapper;
import com.dakkk.dkblog.common.domain.dos.ArticleCategoryRefDO;
import com.dakkk.dkblog.common.domain.dos.ArticleTagRefDO;

/**
 * @author mikey
 * @description 针对表【t_article_tag_ref(文章对应标签关联表)】的数据库操作Mapper
 * @createDate 2024-04-30 15:13:07
 * @Entity com.dakkk.dkblog.common.domain.dos.ArticleTagRefDO
 */
public interface ArticleTagRefMapper extends InsertBatchMapper<ArticleTagRefDO> {
    /**
     * 根据文章 ID 删除文章-分类关联表的记录
     */
    default int deleteByArticleId(Long articleId) {
        return delete(Wrappers.<ArticleTagRefDO>lambdaQuery()
                .eq(ArticleTagRefDO::getArticleId, articleId));
    }
}




