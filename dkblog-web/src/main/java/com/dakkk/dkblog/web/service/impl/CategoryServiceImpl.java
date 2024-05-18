package com.dakkk.dkblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dakkk.dkblog.common.domain.dos.ArticleCategoryRefDO;
import com.dakkk.dkblog.common.domain.dos.ArticleDO;
import com.dakkk.dkblog.common.domain.dos.CategoryDO;
import com.dakkk.dkblog.common.domain.mapper.ArticleCategoryRefMapper;
import com.dakkk.dkblog.common.domain.mapper.ArticleMapper;
import com.dakkk.dkblog.common.domain.mapper.CategoryMapper;
import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.exception.BizException;
import com.dakkk.dkblog.common.utils.PageResponse;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.convert.ArticleConvert;
import com.dakkk.dkblog.web.model.vo.category.FindCategoryArticlePageListReqVO;
import com.dakkk.dkblog.web.model.vo.category.FindCategoryArticlePageListRspVO;
import com.dakkk.dkblog.web.model.vo.category.FindCategoryListRspVO;
import com.dakkk.dkblog.web.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ClassName: CategoryServiceImpl
 * Package: com.dakkk.dkblog.web.service.impl
 *
 * @Create 2024/5/10 16:39
 * @Author dakkk
 * Description:
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    /**
     * 获取分类列表
     *
     * @return
     */
    @Override
    public Response findCategoryList() {
        // 查询所有分类
        List<CategoryDO> categoryDOS = categoryMapper.selectList(Wrappers.emptyWrapper());
        // DO转VO
        List<FindCategoryListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(categoryDOS)) {
            vos = categoryDOS.stream().map(categoryDO -> FindCategoryListRspVO.builder()
                            .id(categoryDO.getId())
                            .name(categoryDO.getName())
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(vos);
    }

    /**
     * 获取分类下的文章分页数据
     * @param findCategoryArticlePageListReqVO
     * @return
     */
    @Override
    public Response findCategoryArticlePageList(FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO) {
        Long current = findCategoryArticlePageListReqVO.getCurrent();
        Long size = findCategoryArticlePageListReqVO.getSize();
        Long categoryId = findCategoryArticlePageListReqVO.getId();

        CategoryDO categoryDO = categoryMapper.selectById(categoryId);
        // 判断该分类是否存在
        if (Objects.isNull(categoryDO)){
            log.warn("==> 该分类不存在，categoryId：{}",categoryId);
            throw new BizException(ResponseErrorCodeEnum.CATEGORY_NOT_EXISTED);
        }

        // 先查询该分类下所有关联的文章 ID
        List<ArticleCategoryRefDO> articleCategoryRefDOS = articleCategoryRefMapper.selectListByCategoryId(categoryId);

        // 若该分类下未发布任何文章
        if (CollectionUtils.isEmpty(articleCategoryRefDOS)){
            log.info("==> 该分类下还为发布任何文章，categoryId：{}",categoryId);
            return PageResponse.success(null,null);
        }

        // 使用stream，从articleCategoryRefDOS获取文章id的集合
        List<Long> articleIds = articleCategoryRefDOS.stream().map(ArticleCategoryRefDO::getArticleId).collect(Collectors.toList());

        // 根据文章ID的集合，查询文章的分页数据
        Page<ArticleDO> page = articleMapper.selectPageListByArticleIds(current, size, articleIds);
        List<ArticleDO> articleDOS = page.getRecords();

        // DO 转 VO
        List<FindCategoryArticlePageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(articleDOS)){
            vos = articleDOS.stream().map(
                    articleDO -> ArticleConvert.INSTANCE.convertDO2CategoryArticleVO(articleDO))
                    .collect(Collectors.toList());
        }

        return PageResponse.success(page,vos);
    }
}
