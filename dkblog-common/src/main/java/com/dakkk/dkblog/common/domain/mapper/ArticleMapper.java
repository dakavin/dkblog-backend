package com.dakkk.dkblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dakkk.dkblog.common.domain.dos.ArticleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author mikey
 * @description 针对表【t_article(文章表)】的数据库操作Mapper
 * @createDate 2024-04-30 15:12:31
 * @Entity com.dakkk.dkblog.common.domain.dos.ArticleDO
 */
public interface ArticleMapper extends BaseMapper<ArticleDO> {
    /**
     * 分页查询的方法
     */
    default Page<ArticleDO> selectPageList(Long current, Long size, String title,
                                           LocalDateTime startDateTime, LocalDateTime endDateTime) {
        // 分页对象（查询第几页，每页多少数据）
        Page<ArticleDO> page = new Page<>(current, size);

        // 构建查询条件
        LambdaQueryWrapper<ArticleDO> lqw = Wrappers.<ArticleDO>lambdaQuery()
                // like 模糊查询文章标题
                .like(StringUtils.isNotBlank(title), ArticleDO::getTitle, title)
                // 大于等于起始时间
                .ge(Objects.nonNull(startDateTime), ArticleDO::getCreateTime, startDateTime)
                // 小于等于结束时间
                .le(Objects.nonNull(endDateTime), ArticleDO::getCreateTime, endDateTime)
                // 按照文章创建时间，倒叙排列
                .orderByDesc(ArticleDO::getCreateTime);
        return selectPage(page, lqw);
    }

    /**
     * 根据文章id，批量分页查询
     */
    default Page<ArticleDO> selectPageListByArticleIds(Long current, Long size, List<Long> articleIds){
        // 分页对象（查询第几页、每页多少数据）
        Page<ArticleDO> page = new Page<>(current,size);
        // 构建查询条件
        LambdaQueryWrapper<ArticleDO> lqw = Wrappers.<ArticleDO>lambdaQuery()
                // 批量查询
                .in(ArticleDO::getId,articleIds)
                //按创建时间倒序
                .orderByDesc(ArticleDO::getCreateTime);

        return selectPage(page,lqw);
    }

    /**
     * 查询当前文章的上一篇文章
     */
    default ArticleDO selectPreArticle(Long articleId){
        return selectOne(Wrappers.<ArticleDO>lambdaQuery()
                // 按照文章 ID 升序排列
                .orderByDesc(ArticleDO::getId)
                // 查询比当前文章 ID 大的
                .lt(ArticleDO::getId,articleId)
                // 第一条记录即为上一篇文章
                .last("limit 1")
        );
    }
    /**
     * 查询当前文章的下一篇文章
     */
    default ArticleDO selectNextArticle(Long articleId){
        return selectOne(Wrappers.<ArticleDO>lambdaQuery()
                // 按照文章 ID 降序排列
                .orderByAsc(ArticleDO::getId)
                // 查询比当前文章 ID 小的
                .gt(ArticleDO::getId,articleId)
                // 第一条记录即为下一篇文章
                .last("limit 1")
        );
    }
}




