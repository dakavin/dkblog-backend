package com.dakkk.dkblog.admin.convert;

import com.dakkk.dkblog.admin.model.vo.article.FindArticlePageListRspVO;
import com.dakkk.dkblog.common.domain.dos.ArticleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ClassName: ArticleConvert
 * Package: com.dakkk.dkblog.web.convert
 *
 * @Create 2024/5/10 10:48
 * @Author dakkk
 * Description:
 */
@Mapper
public interface ArticleConvert {
    /**
     * 初始化 convert 实例
     */
    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);
    /**
     * DO 转 VO 的方法
     */
    FindArticlePageListRspVO convertDO2VO(ArticleDO bean);
}
