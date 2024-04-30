package com.dakkk.dkblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章表
 * @TableName t_article
 */
@TableName(value ="t_article")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDO implements Serializable {
    /**
     * 文章id
     */
    @TableId(type = IdType.AUTO)
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
     * 文章摘要
     */
    private String summary;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后一次更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标志位：0：未删除 1：已删除
     */
    private Integer isDeleted;

    /**
     * 被阅读次数
     */
    private Integer readNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}