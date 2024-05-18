package com.dakkk.dkblog.web.model.vo.article;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: FindArticleDetailReqVO
 * Package: com.dakkk.dkblog.web.model.vo.article
 *
 * @Create 2024/5/18 22:17
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询某个文章的详情内容接口的入参VO")
public class FindArticleDetailReqVO {
    /**
     * 文章ID
     */
    private Long articleId;
}
