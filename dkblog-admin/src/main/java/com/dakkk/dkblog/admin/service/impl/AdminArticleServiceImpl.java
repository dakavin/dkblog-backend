package com.dakkk.dkblog.admin.service.impl;

import com.dakkk.dkblog.admin.model.vo.article.DeleteArticleReqVO;
import com.dakkk.dkblog.admin.model.vo.article.PublishArticleReqVO;
import com.dakkk.dkblog.admin.service.AdminArticleService;
import com.dakkk.dkblog.common.domain.dos.*;
import com.dakkk.dkblog.common.domain.mapper.*;
import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.exception.BizException;
import com.dakkk.dkblog.common.utils.Response;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Resource
    TagMapper tagMapper;
    @Resource
    ArticleTagRefMapper articleTagRefMapper;

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
        if (Objects.isNull(categoryDO)) {
            log.warn("==> 分类不存在，categoryId：{}", categoryId);
            throw new BizException(ResponseErrorCodeEnum.CATEGORY_NOT_EXISTED);
        }

        ArticleCategoryRefDO articleCategoryRefDO = ArticleCategoryRefDO.builder()
                .articleId(articleDOId)
                .categoryId(categoryId)
                .build();
        articleCategoryRefMapper.insert(articleCategoryRefDO);

        // int i = 1/0;

        // 4. 保存文章关联的标签集合
        List<String> publishTags = publishArticleReqVO.getTags();
        insertTags(articleDOId, publishTags);

        return Response.success();
    }

    /**
     * 保存文章关联标签的方法
     */
    private void insertTags(Long articleDOId, List<String> publishTags) {
        // 筛选提交的标签（标签表中不存在的标签）
        List<String> notExistTags = null;
        // 筛选提交的标签（标签表中存在的标签）
        List<String> existedTags = null;

        // 查询出所有的标签
        List<TagDO> tagDOS = tagMapper.selectList(null);

        // 如果表中还没有添加任何标签
        if (CollectionUtils.isEmpty(tagDOS)) {
            notExistTags = publishTags;
        } else {
            List<String> tagIds = tagDOS.stream().map(tagDO -> String.valueOf(tagDO.getId()))
                    .collect(Collectors.toList());
            // 筛选标签
            // 通过id来筛选标签，id相等即为已存在
            existedTags = publishTags.stream().filter(publishTag -> tagIds.contains(publishTag))
                    .collect(Collectors.toList());
            // id不存在于tagIds，即不是存在的标签
            notExistTags = publishTags.stream().filter(publishTag -> !tagIds.contains(publishTag))
                    .collect(Collectors.toList());
            // 补充逻辑：除了比较id外，字符串还没有比较，比如传入java，tag表中有Java的情况
            Map<String, Long> tagNameIdMap = tagDOS.stream().collect(Collectors.toMap(tagDO ->
                    tagDO.getName().toLowerCase(), TagDO::getId));
            // 使用迭代器进行安全的删除操作
            Iterator<String> iterator = notExistTags.iterator();
            while (iterator.hasNext()) {
                String notExistTag = iterator.next();
                // 转小写，若和Map相同的key，则表示该标签已存在
                if (tagNameIdMap.containsKey(notExistTag.toLowerCase())) {
                    // 从不存在的标签集合中删除
                    iterator.remove();
                    // 添加到存在的标签集合中
                    existedTags.add(String.valueOf(tagNameIdMap.get(notExistTag.toLowerCase())));
                }
            }
        }

        // 将存在标签表中的标签集合，直接入 文章-标签关联库
        if (!CollectionUtils.isEmpty(existedTags)) {
            List<ArticleTagRefDO> articleTagRefDOS = Lists.newArrayList();
            existedTags.forEach(tagId -> {
                ArticleTagRefDO articleTagRefDO = ArticleTagRefDO.builder()
                        .articleId(articleDOId)
                        .tagId(Long.valueOf(tagId))
                        .build();
                articleTagRefDOS.add(articleTagRefDO);
            });
            articleTagRefMapper.insertBatchSomeColumn(articleTagRefDOS);
        }

        // 将不存在标签表的标签集合，先入标签库，再入文章-标签库
        if (!CollectionUtils.isEmpty(notExistTags)) {
            List<ArticleTagRefDO> articleTagRefDOS = Lists.newArrayList();
            notExistTags.forEach(tagName -> {
                TagDO tagDO = TagDO.builder()
                        .name(tagName)
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .build();
                tagMapper.insert(tagDO);

                // 拿到插入后的id
                Long tagId = tagDO.getId();

                // 构建文章-标签关联表的对象
                ArticleTagRefDO articleTagRefDO = ArticleTagRefDO.builder()
                        .tagId(tagId)
                        .articleId(articleDOId)
                        .build();
                articleTagRefDOS.add(articleTagRefDO);
            });
            articleTagRefMapper.insertBatchSomeColumn(articleTagRefDOS);
        }
    }

    /**
     * 删除文章
     *
     * @param ArticleId 文章Id
     * @return 统一返回Response对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response deleteArticle(DeleteArticleReqVO deleteArticleReqVO) {
        Long ArticleId = deleteArticleReqVO.getId();
        // 1. 删除文章
        articleMapper.deleteById(ArticleId);
        // 2. 删除文章内容
        articleContentMapper.deleteByArticleId(ArticleId);
        // 3. 删除文章-分类关联记录
        articleCategoryRefMapper.deleteByArticleId(ArticleId);
        // 4. 删除文章-标签关联记录
        articleTagRefMapper.deleteByArticleId(ArticleId);

        return Response.success();
    }
}
