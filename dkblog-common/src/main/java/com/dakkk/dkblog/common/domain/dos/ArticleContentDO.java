package com.dakkk.dkblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章内容表
 * @TableName t_article_content
 */
@TableName(value ="t_article_content")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleContentDO implements Serializable {
    /**
     * 文章内容id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 正文
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}