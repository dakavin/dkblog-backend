package com.dakkk.dkblog.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dakkk.dkblog.admin.convert.ArticleConvert;
import com.dakkk.dkblog.admin.convert.ArticleDetailConvert;
import com.dakkk.dkblog.admin.model.vo.article.*;
import com.dakkk.dkblog.admin.service.AdminArticleService;
import com.dakkk.dkblog.common.domain.dos.*;
import com.dakkk.dkblog.common.domain.mapper.*;
import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.exception.BizException;
import com.dakkk.dkblog.common.utils.PageResponse;
import com.dakkk.dkblog.common.utils.Response;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
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

    @Override
    public Response findArticlePageList(FindArticlePageListReqVO findArticlePageListReqVO) {
        // 获取当前页、以及每页需要展示的数据数量
        Long current = findArticlePageListReqVO.getCurrent();
        Long size = findArticlePageListReqVO.getSize();
        String title = findArticlePageListReqVO.getTitle();
        LocalDateTime startTime = findArticlePageListReqVO.getStartDateTime();
        LocalDateTime endTime = findArticlePageListReqVO.getEndDateTime();

        // 执行分页查询
        Page<ArticleDO> articleDOPage = articleMapper.selectPageList(current, size, title, startTime, endTime);
        // 返回的实际分页数据
        List<ArticleDO> articleDOS = articleDOPage.getRecords();

        List<FindArticlePageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(articleDOS)) {
            // DO 转 VO
            vos = articleDOS.stream().map(articleDO -> ArticleConvert.INSTANCE.convertDO2VO(articleDO)).collect(Collectors.toList());
            // 拿到所有文章的ID集合
            List<Long> articleIds = articleDOS.stream().map(ArticleDO::getId).collect(Collectors.toList());

            // -->设置VO的分类名称<--
            // 查询所有的分类
            List<CategoryDO> categoryDOS = categoryMapper.selectList(Wrappers.emptyWrapper());
            // 转Map，方便后续根据分类ID获取分类名称
            Map<Long, String> categoryIdNameMap = categoryDOS.stream().collect(Collectors.toMap(CategoryDO::getId, CategoryDO::getName));
            // 根据文章ID集合，批量查询所有的关联的分类记录
            List<ArticleCategoryRefDO> articleCategoryRefDOS = articleCategoryRefMapper.selectByArticleIds(articleIds);
            // 筛选
            vos.forEach(vo -> {
                Long currArticleId = vo.getId();
                // 过滤出当前文章对应关联的分类
                Optional<ArticleCategoryRefDO> optional =
                        articleCategoryRefDOS.stream().filter(ref -> Objects.equals(ref.getArticleId(), currArticleId)).findAny();
                // 若过滤不为空，则将当前vo对应的分类名称进行设置
                if (optional.isPresent()) {
                    ArticleCategoryRefDO articleCategoryRefDO = optional.get();
                    Long categoryId = articleCategoryRefDO.getCategoryId();
                    // 通过分类ID从Map中获取分类的名称,并给VO设置
                    vo.setCategoryName(categoryIdNameMap.get(categoryId));
                }
            });

            // -->设置VO的标签名称<--
            // 查询所有的标签
            List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());
            // 转Map
            Map<Long, String> tagIdNameMap = tagDOS.stream().collect(Collectors.toMap(TagDO::getId, TagDO::getName));
            // 根据文章ID集合，批量查询所有的关联的标签记录
            List<ArticleTagRefDO> articleTagRefDOS = articleTagRefMapper.selectByArticleIds(articleIds);
            // 筛选
            vos.forEach(vo -> {
                Long currArticleId = vo.getId();
                // 过滤出当前文章对应关联的标签
                List<ArticleTagRefDO> articleTagRefDOList =
                        articleTagRefDOS.stream().filter(ref -> Objects.equals(ref.getArticleId(), currArticleId)).collect(Collectors.toList());
                // 将对应的标签转换为标签名
                List<String> tagNames = Lists.newArrayList(); // list必须初始化
                articleTagRefDOList.forEach(articleTagRefDO -> {
                    Long tagId = articleTagRefDO.getTagId();
                    String tagName = tagIdNameMap.get(tagId);
                    tagNames.add(tagName);
                });
                vo.setTagNames(tagNames);
            });

            System.out.println(vos);

            /* 循环中查询数据库，大忌！！！
            vos = articleDOS.stream()
                    .map(articleDO -> {
                        FindArticlePageListRspVO vo = FindArticlePageListRspVO.builder()
                                .id(articleDO.getId())
                                .summary(articleDO.getSummary())
                                .title(articleDO.getTitle())
                                .cover(articleDO.getCover())
                                .createTime(articleDO.getCreateTime())
                                .updateTime(articleDO.getUpdateTime())
                                .build();
                        // 查询文章所属分类，并设置到vo中去
                        CategoryDO categoryDO = categoryMapper.selectById(articleCategoryRefMapper.selectByArticleId(articleDO.getId()).getCategoryId());
                        vo.setCategoryName(categoryDO.getName());
                        // 查询文章所属标签，并设置到vo中去
                        List<ArticleTagRefDO> articleTagRefDOS = articleTagRefMapper.selectByArticleId(articleDO.getId());
                        List<Long> tagIds = articleTagRefDOS.stream().map(ArticleTagRefDO::getTagId).collect(Collectors.toList());
                        List<String> tagNames = tagIds.stream().map(
                                tagId -> tagMapper.selectById(tagId).getName()
                        ).collect(Collectors.toList());
                        vo.setTagNames(tagNames);
                        return vo;
                    })
                    .collect(Collectors.toList());
            */
        }
        return PageResponse.success(articleDOPage, vos);
    }

    @Override
    public Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO) {
        Long articleId = findArticleDetailReqVO.getId();

        // 获取文章相关信息
        ArticleDO articleDO = articleMapper.selectById(articleId);

        if (Objects.isNull(articleDO)) {
            log.warn("==> 查询的文章不存在，articleId：{}", articleId);
            throw new BizException(ResponseErrorCodeEnum.ARTICLE_NOT_FOUND);
        }

        // 获取文章的内容
        ArticleContentDO articleContentDO = articleContentMapper.selectBtArticleId(articleId);

        // 获取文章的分类
        ArticleCategoryRefDO articleCategoryRefDO = articleCategoryRefMapper.selectByArticleId(articleId);

        // 获取其文章的标签
        List<ArticleTagRefDO> articleTagRefDOS = articleTagRefMapper.selectByArticleId(articleId);
        List<Long> tagIds = articleTagRefDOS.stream().map(ArticleTagRefDO::getTagId).collect(Collectors.toList());

        // DO 转 VO
        // System.out.println(ArticleDetailConvert.INSTANCE);
        FindArticleDetailRspVO vo = ArticleDetailConvert.INSTANCE.convertDO2VO(articleDO);
        vo.setContent(articleContentDO.getContent());
        vo.setCategoryId(articleCategoryRefDO.getCategoryId());
        vo.setTagIds(tagIds);

        return Response.success(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateArticle(UpdateArticleReqVO updateArticleReqVO) {
        Long articleId = updateArticleReqVO.getId();

        // VO 转 ArticleDO，并更新
        ArticleDO articleDO = ArticleDO.builder()
                .id(articleId)
                .title(updateArticleReqVO.getTitle())
                .cover(updateArticleReqVO.getCover())
                .summary(updateArticleReqVO.getSummary())
                .updateTime(LocalDateTime.now())
                .build();
        int count = articleMapper.updateById(articleDO);

        // 判断更新是否成功，来判断该文章是否存在
        if (count == 0) {
            log.warn("==> 该文章不存在，articleId：{}", articleId);
            throw new BizException(ResponseErrorCodeEnum.ARTICLE_NOT_FOUND);
        }

        // VO 转 ArticleContentDO，并更新
        ArticleContentDO articleContentDO = ArticleContentDO.builder()
                .articleId(articleId)
                .content(updateArticleReqVO.getContent())
                .build();
        articleContentMapper.updateByArticleId(articleContentDO);

        // 更新文章分类
        Long categoryId = updateArticleReqVO.getCategoryId();
        // 校验分类是否存在
        CategoryDO categoryDO = categoryMapper.selectById(categoryId);
        if (Objects.isNull(categoryDO)) {
            log.warn("==> 分类不存在，categoryId：{}", categoryId);
            throw new BizException(ResponseErrorCodeEnum.CATEGORY_NOT_EXISTED);
        }

        // 先删除该文章关联的分类记录，再插入
        articleCategoryRefMapper.deleteByArticleId(articleId);
        ArticleCategoryRefDO articleCategoryRefDO = ArticleCategoryRefDO.builder()
                .articleId(articleId)
                .categoryId(categoryId)
                .build();
        articleCategoryRefMapper.insert(articleCategoryRefDO);

        // 保存文章关联的标签集合
        // 先删除文章对应的标签
        articleTagRefMapper.deleteByArticleId(articleId);
        List<String> publishTags = updateArticleReqVO.getTags();
        insertTags(articleId, publishTags);

        return Response.success();
    }
}
