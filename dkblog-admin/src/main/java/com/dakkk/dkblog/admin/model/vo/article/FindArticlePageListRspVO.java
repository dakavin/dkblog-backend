package com.dakkk.dkblog.admin.model.vo.article;

import com.dakkk.dkblog.common.domain.dos.TagDO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: FindArticlePageListRspVO
 * Package: com.dakkk.dkblog.admin.model.vo.article
 *
 * @Create 2024/5/4 15:05
 * @Author dakkk
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindArticlePageListRspVO {
    /**
     * 文章 ID
     */
    private Long id;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章方面
     */
    private String cover;
    /**
     * 文章发布时间
     */
    private LocalDateTime createTime;
    /**
     * 文章更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 文章所属分类
     */
    private String categoryName;
    /**
     * 标签 ID 集合
     */
    private List<String> tagNames;
}
