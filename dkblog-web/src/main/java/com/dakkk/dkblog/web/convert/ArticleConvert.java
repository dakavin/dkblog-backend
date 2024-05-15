package com.dakkk.dkblog.web.convert;

import com.dakkk.dkblog.common.domain.dos.ArticleDO;
import com.dakkk.dkblog.web.model.vo.article.FindIndexArticlePageListRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
    @Mapping(target = "createDate",expression =
            "java(java.time.LocalDate.from(bean.getCreateTime()))")
    FindIndexArticlePageListRspVO convertDO2VO(ArticleDO bean);
}
