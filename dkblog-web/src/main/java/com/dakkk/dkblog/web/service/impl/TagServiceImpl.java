package com.dakkk.dkblog.web.service.impl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dakkk.dkblog.common.domain.dos.ArticleDO;
import com.dakkk.dkblog.common.domain.dos.ArticleTagRefDO;
import com.dakkk.dkblog.common.domain.dos.TagDO;
import com.dakkk.dkblog.common.domain.mapper.ArticleMapper;
import com.dakkk.dkblog.common.domain.mapper.ArticleTagRefMapper;
import com.dakkk.dkblog.common.domain.mapper.TagMapper;
import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.exception.BizException;
import com.dakkk.dkblog.common.utils.PageResponse;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.convert.ArticleConvert;
import com.dakkk.dkblog.web.model.vo.category.FindCategoryListRspVO;
import com.dakkk.dkblog.web.model.vo.tag.FindTagArticlePageListReqVO;
import com.dakkk.dkblog.web.model.vo.tag.FindTagArticlePageListRspVO;
import com.dakkk.dkblog.web.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTagRefMapper articleTagRefMapper;

    /**
     * 获取分类列表
     *
     * @return
     */
    @Override
    public Response findTagList() {
        // 查询所有分类
        List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());
        // DO转VO
        List<FindCategoryListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream().map(categoryDO -> FindCategoryListRspVO.builder()
                            .id(categoryDO.getId())
                            .name(categoryDO.getName())
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(vos);
    }

    /**
     * 获取某个标签下的文章分页数据
     *
     * @param findTagArticlePageListReqVO
     * @return
     */
    @Override
    public Response findTagArticlePageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO) {
        Long current = findTagArticlePageListReqVO.getCurrent();
        Long size = findTagArticlePageListReqVO.getSize();
        // 获取入参的标签ID
        Long tagId = findTagArticlePageListReqVO.getId();

        // 判断标签是否存在
        TagDO tagDO = tagMapper.selectById(tagId);
        if (ObjectUtils.isNull(tagDO)) {
            log.warn("==> 该标签不存在，tagID：{}", tagId);
            throw new BizException(ResponseErrorCodeEnum.TAG_NOT_EXISTED);
        }

        // 先查询该标签下所有关联的文章 ID
        List<ArticleTagRefDO> articleTagRefDOS = articleTagRefMapper.selectListByTagId(tagId);
        // 若该标签下未发布任何文章
        if (CollectionUtils.isEmpty(articleTagRefDOS)) {
            log.info("==> 该标签下还为发布任何文章，tagID：{}", tagId);
            return PageResponse.success(new Page(current, size), null);
        }

        // 提取所有的文章ID
        List<Long> articleIds = articleTagRefDOS.stream().map(ArticleTagRefDO::getArticleId).collect(Collectors.toList());

        // 根据文章ID集合查询文章分页数据
        Page<ArticleDO> page = articleMapper.selectPageListByArticleIds(current, size, articleIds);
        List<ArticleDO> articleDOS = page.getRecords();

        // DO 转 VO
        List<FindTagArticlePageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(articleDOS)) {
            vos = articleDOS.stream()
                    .map(articleDO -> ArticleConvert.INSTANCE.convertDO2TagArticleVO(articleDO))
                    .collect(Collectors.toList());
        }
        return PageResponse.success(page, vos);
    }
}
