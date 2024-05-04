package com.dakkk.dkblog.admin.model.vo.article;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
@ApiModel("分页查询文章接口的出参VO")
public class FindArticlePageListRspVO {
    /**
     * 文章 ID
     */
    private Long id;
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
}
