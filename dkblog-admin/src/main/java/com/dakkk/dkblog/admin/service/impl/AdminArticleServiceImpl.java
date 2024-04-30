package com.dakkk.dkblog.admin.service.impl;

import com.dakkk.dkblog.admin.model.vo.article.PublishArticleReqVO;
import com.dakkk.dkblog.admin.service.AdminArticleService;
import com.dakkk.dkblog.common.domain.dos.ArticleCategoryRefDO;
import com.dakkk.dkblog.common.domain.dos.ArticleContentDO;
import com.dakkk.dkblog.common.domain.dos.ArticleDO;
import com.dakkk.dkblog.common.domain.dos.CategoryDO;
import com.dakkk.dkblog.common.domain.mapper.*;
import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.exception.BizException;
import com.dakkk.dkblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * ClassName: AdminArticleServiceImpl
 * Package: com.dakkk.dkblog.admin.service.impl
 *
 * @Create 2024/4/30 15:23
 * @Author dakkk
 * Description:
 */
@Service
@Slf4j
public class AdminArticleServiceImpl implements AdminArticleService {
    @Resource
    ArticleMapper articleMapper;
    @Resource
    ArticleContentMapper articleContentMapper;
    @Resource
    ArticleCategoryRefMapper articleCategoryRefMapper;
    @Resource
    CategoryMapper categoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response publishArticle(PublishArticleReqVO publishArticleReqVO) {
        // 1. VO 转 ArticleDO，并保存
        ArticleDO articleDO = ArticleDO.builder()
                .title(publishArticleReqVO.getTitle())
                .cover(publishArticleReqVO.getCover())
                .summary(publishArticleReqVO.getSummary())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        articleMapper.insert(articleDO);

        // 拿到插入记录的主键 ID
        Long articleDOId = articleDO.getId();

        // 2. VO 转 ArticleContentDO，并保存
        ArticleContentDO articleContentDO = ArticleContentDO.builder()
                .articleId(articleDOId)
                .content(publishArticleReqVO.getContent())
                .build();
        articleContentMapper.insert(articleContentDO);

        // 3. 处理文章关联的分类
        Long categoryId = publishArticleReqVO.getCategoryId();
        // 3.1 校验提交的分类是否存在
        CategoryDO categoryDO = categoryMapper.selectById(categoryId);
        if (Objects.isNull(categoryDO)){
            log.warn("==> 分类不存在，categoryId：{}",categoryId);
            throw new BizException(ResponseErrorCodeEnum.CATEGORY_NOT_EXISTED);
        }

        ArticleCategoryRefDO articleCategoryRefDO = ArticleCategoryRefDO.builder()
                .articleId(articleDOId)
                .categoryId(categoryId)
                .build();
        articleCategoryRefMapper.insert(articleCategoryRefDO);

        // int i = 1/0;

        // 4. 保存文章关联的标签集合
        List<String> publishTags =publishArticleReqVO.getTags();
        insertTags(publishTags);

        return Response.success();
    }

    /**
     * 保存文章关联标签的方法
     */
    private void insertTags(List<String> publishTags) {
        // todo
    }

}
