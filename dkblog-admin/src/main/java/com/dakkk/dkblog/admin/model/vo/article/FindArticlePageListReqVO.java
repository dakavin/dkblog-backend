package com.dakkk.dkblog.admin.model.vo.article;

import com.dakkk.dkblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: FindArticlePageListReqVO
 * Package: com.dakkk.dkblog.admin.model.vo.article
 *
 * @Create 2024/5/4 15:03
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("分页查询文章接口的入参VO")
public class FindArticlePageListReqVO extends BasePageQuery {
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章发布起始日期
     */
    private LocalDateTime startDateTime;
    /**
     * 文章发布结束日期
     */
    private LocalDateTime endDateTime;
}
