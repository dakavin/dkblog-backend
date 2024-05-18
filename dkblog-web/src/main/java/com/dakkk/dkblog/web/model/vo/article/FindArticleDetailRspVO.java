package com.dakkk.dkblog.web.model.vo.article;

import com.dakkk.dkblog.web.model.vo.tag.FindTagListRspVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: FindArticleDetailRspVO
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
public class FindArticleDetailRspVO {
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章正文（HTML）
     */
    private String content;
    /**
     * 文章发布时间
     */
    private LocalDateTime createTime;
    /**
     * 文章更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 文章所属分类ID
     */
    private Long categoryId;
    /**
     * 文章所属分类名称
     */
    private String categoryName;
    /**
     * 文章阅读量
     */
    private Integer readNum;
    /**
     * 文章所属标签集合
     */
    private List<FindTagListRspVO> tags;
    /**
     * 上一篇文章
     */
    private FindPreNextArticleRspVO preArticle;
    /**
     * 下一篇文章
     */
    private FindPreNextArticleRspVO nextArticle;
}
