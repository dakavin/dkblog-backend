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
 * 文章标签表
 * @TableName t_tag
 */
@TableName(value ="t_tag")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TagDO implements Serializable {
    /**
     * 标签id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签的描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后一次更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标志位：0：未删除 1：已删除
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}