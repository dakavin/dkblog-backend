package com.dakkk.dkblog.web.model.vo.article;

import com.dakkk.dkblog.web.model.vo.category.FindCategoryListRspVO;
import com.dakkk.dkblog.web.model.vo.tag.FindTagListRspVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: FindIndexArticlePageListRspVO
 * Package: com.dakkk.dkblog.web.model.vo.article
 *
 * @Create 2024/5/10 10:36
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindIndexArticlePageListRspVO {
    private Long id;
    private String cover;
    private String title;
    private LocalDateTime createTime;
    private String summary;
    /**
     * 文章分类
     */
    private FindCategoryListRspVO category;
    /**
     * 文章标签
     */
    private List<FindTagListRspVO> tags;
}
