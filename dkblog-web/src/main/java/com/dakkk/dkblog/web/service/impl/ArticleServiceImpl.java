package com.dakkk.dkblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dakkk.dkblog.common.domain.dos.*;
import com.dakkk.dkblog.common.domain.mapper.*;
import com.dakkk.dkblog.common.utils.PageResponse;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.convert.ArticleConvert;
import com.dakkk.dkblog.web.model.vo.article.FindIndexArticlePageListReqVO;
import com.dakkk.dkblog.web.model.vo.article.FindIndexArticlePageListRspVO;
import com.dakkk.dkblog.web.model.vo.category.FindCategoryListRspVO;
import com.dakkk.dkblog.web.model.vo.tag.FindTagListRspVO;
import com.dakkk.dkblog.web.service.ArticleService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ClassName: ArticleServiceImpl
 * Package: com.dakkk.dkblog.web.service.impl
 *
 * @Create 2024/5/10 10:41
 * @Author dakkk
 * Description:
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private TagMapper tagMapper;
    @Resource
    private ArticleCategoryRefMapper articleCategoryRefMapper;
    @Resource
    private ArticleTagRefMapper articleTagRefMapper;

    /**
     * 获取首页文章分页数据
     *
     * @param findIndexArticlePageListReqVO
     * @return
     */
    @Override
    public Response findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO) {
        Long current = findIndexArticlePageListReqVO.getCurrent();
        Long size = findIndexArticlePageListReqVO.getSize();

        // -->  1. 分页查询文章主体记录  <--
        Page<ArticleDO> articleDOPage = articleMapper.selectPageList(current, size, null, null, null);
        // 返回的分页数据
        List<ArticleDO> articleDOS = articleDOPage.getRecords();

        List<FindIndexArticlePageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(articleDOS)) {
            // 文章 DO 转 VO
            vos = articleDOS.stream().map(articleDO -> ArticleConvert.INSTANCE.convertDO2VO(articleDO))
                    .collect(Collectors.toList());

            // 拿到所有文章的ID集合
            List<Long> articleIds = articleDOS.stream().map(ArticleDO::getId).collect(Collectors.toList());

            // todo 有空对比自己写的代码 AdminArticleServiceImpl.java 分析两种写法的区别
            // 下面的代码对数据库压力小，因为每次都是从数据库查询
            // -->  2. 设置文章所属分类  <--
            // 查询所有分类
            List<CategoryDO> categoryDOS = categoryMapper.selectList(Wrappers.emptyWrapper());
            // List 转 Map，方便通过 分类ID 获取对应的 分类名称
            Map<Long, String> categoryIdNameMap = categoryDOS.stream().collect(Collectors.toMap(CategoryDO::getId, CategoryDO::getName));
            // 根据文章 Id 批量查询所有与分类关联的记录
            List<ArticleCategoryRefDO> articleCategoryRefDOS = articleCategoryRefMapper.selectByArticleIds(articleIds);
            // 从上述数据中，过滤去每一个文章对应的分类名称
            vos.forEach(vo -> {
                Long currArticleId = vo.getId();
                // 过滤出当前文章对应的分类关联数据
                Optional<ArticleCategoryRefDO> optional
                        = articleCategoryRefDOS.stream().filter(rel -> Objects.equals(rel.getArticleId(), currArticleId)).findAny();
                // 若找到了对应的数据
                if (optional.isPresent()) {
                    ArticleCategoryRefDO articleCategoryRefDO = optional.get();
                    Long categoryId = articleCategoryRefDO.getCategoryId();
                    // 通过分类 ID 从 map 中拿到对应的分类名称
                    String categoryName = categoryIdNameMap.get(categoryId);
                    FindCategoryListRspVO findCategoryListRspVO = FindCategoryListRspVO.builder()
                            .id(categoryId)
                            .name(categoryName)
                            .build();
                    // 设置到当前 vo 类中
                    vo.setCategory(findCategoryListRspVO);
                }
            });
            // -->  3. 设置文章所属标签  <--
            // 查询所有标签
            List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());
            // List 转 Map，方便通过 标签id 获取对应的 标签名称
            Map<Long, String> tagIdNameMap = tagDOS.stream().collect(Collectors.toMap(TagDO::getId, TagDO::getName));
            // 根据文章 Id 批量查询所有与标签关联的记录
            List<ArticleTagRefDO> articleTagRefDOS = articleTagRefMapper.selectByArticleIds(articleIds);
            // 过滤出每一个文章对应的标签集合
            vos.forEach(vo -> {
                Long currArticleId = vo.getId();
                // 过滤出当前文章对应的标签关联数据
                List<ArticleTagRefDO> articleTagRefList
                        = articleTagRefDOS.stream().filter(ref -> Objects.equals(ref.getArticleId(), currArticleId)).collect(Collectors.toList());
                // 将关联的标签ID转化为标签Name，并存入VO中
                List<FindTagListRspVO> findTagListRspVOS = Lists.newArrayList();
                articleTagRefList.forEach(articleTagRefDO -> {
                    Long tagId = articleTagRefDO.getTagId();
                    String tagName = tagIdNameMap.get(tagId);

                    findTagListRspVOS.add(FindTagListRspVO.builder()
                                    .id(tagId)
                                    .name(tagName)
                            .build());
                });
                // 存入VO中
                vo.setTags(findTagListRspVOS);
            });
        }
        return PageResponse.success(articleDOPage,vos);
    }
}
