package com.dakkk.dkblog.admin.convert;

import com.dakkk.dkblog.admin.model.vo.article.FindArticleDetailRspVO;
import com.dakkk.dkblog.common.domain.dos.ArticleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ClassName: ArticleDetailConvert
 * Package: com.dakkk.dkblog.admin
 *
 * @Create 2024/5/4 16:42
 * @Author dakkk
 * Description: 文章详情转换
 * 接口中的属性都是 public static final 的; 方法都是public abstract的
 */
@Mapper
public interface ArticleDetailConvert {
    /**
     * 初始化 convert 实例(获取该类自动生成的实现类的实例)
     */
    ArticleDetailConvert INSTANCE = Mappers.getMapper(ArticleDetailConvert.class);

    /**
     * 将 DO 转 VO
     */
    FindArticleDetailRspVO convertDO2VO(ArticleDO bean);
}
