package com.dakkk.dkblog.admin.model.vo.article;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: FindArticleDetailRspVO
 * Package: com.dakkk.dkblog.admin.model.vo.article
 *
 * @Create 2024/5/4 15:33
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArticleDetailRspVO {
    /**
     * 文章 ID
     */
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章封面
     */
    private String cover;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 分类 ID
     */
    private Long categoryId;

    /**
     * 标签 ID 集合
     */
    private List<Long> tagIds;

    /**
     * 摘要
     */
    private String summary;
}
