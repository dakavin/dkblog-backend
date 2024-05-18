package com.dakkk.dkblog.web.model.vo.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: FindPreNextArticleRspVO
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
public class FindPreNextArticleRspVO {
    /**
     * 文章ID
     */
    private Long articleId;
    /**
     * 文章标题
     */
    private String title;
}
