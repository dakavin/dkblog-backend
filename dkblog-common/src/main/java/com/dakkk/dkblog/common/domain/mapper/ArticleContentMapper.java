package com.dakkk.dkblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dakkk.dkblog.common.domain.dos.ArticleContentDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.sql.Wrapper;

/**
 * @author mikey
 * @description 针对表【t_article_content(文章内容表)】的数据库操作Mapper
 * @createDate 2024-04-30 15:13:03
 * @Entity com.dakkk.dkblog.common.domain.dos.ArticleContentDO
 */
public interface ArticleContentMapper extends BaseMapper<ArticleContentDO> {
    /**
     * 根据文章 ID 删除文章-内容关联表的记录
     */
    default int deleteByArticleId(Long articleId) {
        return delete(Wrappers.<ArticleContentDO>lambdaQuery()
                .eq(ArticleContentDO::getArticleId, articleId));
    }

    /**
     * 根据文章 ID 查询文章内容
     */
    default ArticleContentDO selectBtArticleId(Long articleId){
        return selectOne(Wrappers.<ArticleContentDO>lambdaQuery()
                .eq(ArticleContentDO::getArticleId,articleId));
    }

    /**
     * 通过文章 ID 更新文章内容
     */
    default int updateByArticleId(ArticleContentDO articleContentDO){
        return update(articleContentDO,Wrappers.<ArticleContentDO>lambdaQuery()
                .eq(ArticleContentDO::getArticleId,articleContentDO.getArticleId()));
    }
}




