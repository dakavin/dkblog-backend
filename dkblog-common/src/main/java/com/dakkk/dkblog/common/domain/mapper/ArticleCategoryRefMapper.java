package com.dakkk.dkblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dakkk.dkblog.common.domain.dos.ArticleCategoryRefDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dakkk.dkblog.common.domain.dos.ArticleContentDO;

/**
 * @author mikey
 * @description 针对表【t_article_category_ref(文章所属分类关联表)】的数据库操作Mapper
 * @createDate 2024-04-30 15:12:58
 * @Entity com.dakkk.dkblog.common.domain.dos.ArticleCategoryRefDO
 */
public interface ArticleCategoryRefMapper extends BaseMapper<ArticleCategoryRefDO> {
    /**
     * 根据文章 ID 删除文章-分类关联表的记录
     */
    default int deleteByArticleId(Long articleId) {
        return delete(Wrappers.<ArticleCategoryRefDO>lambdaQuery()
                .eq(ArticleCategoryRefDO::getArticleId, articleId));
    }
}




