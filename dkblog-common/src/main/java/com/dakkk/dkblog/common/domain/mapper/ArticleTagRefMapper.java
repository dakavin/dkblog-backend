package com.dakkk.dkblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dakkk.dkblog.common.config.InsertBatchMapper;
import com.dakkk.dkblog.common.domain.dos.ArticleCategoryRefDO;
import com.dakkk.dkblog.common.domain.dos.ArticleTagRefDO;

import java.util.List;

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
    /**
     * 根据文章 ID 查询文章标签
     */
    default List<ArticleTagRefDO> selectByArticleId(Long articleId){
        return selectList(Wrappers.<ArticleTagRefDO>lambdaQuery()
                .eq(ArticleTagRefDO::getArticleId,articleId));
    }

    /**
     * 根据标签 ID 查询
     */
    default ArticleTagRefDO selectOneByTagId(Long tagId){
        return selectOne(Wrappers.<ArticleTagRefDO>lambdaQuery()
                .eq(ArticleTagRefDO::getTagId,tagId)
                .last("LIMIT 1"));
    }

    /**
     * 根据文章ID的list集合，批量查询
     */
    default List<ArticleTagRefDO> selectByArticleIds(List<Long> articleIds){
        return selectList(Wrappers.<ArticleTagRefDO>lambdaQuery()
                // 注意是 in
                .in(ArticleTagRefDO::getArticleId,articleIds));
    }
}




