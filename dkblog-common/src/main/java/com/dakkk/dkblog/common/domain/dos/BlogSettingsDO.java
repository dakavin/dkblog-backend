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
 * 博客设置表
 * @TableName t_blog_settings
 */
@TableName(value ="t_blog_settings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogSettingsDO implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 博客Logo
     */
    private String logo;

    /**
     * 博客名称
     */
    private String name;

    /**
     * 作者名
     */
    private String author;

    /**
     * 介绍语
     */
    private String introduction;

    /**
     * 作者头像
     */
    private String avatar;

    /**
     * Gitee 主页访问地址
     */
    private String giteeHomepage;

    /**
     * GitHub 主页访问地址
     */
    private String githubHomepage;

    /**
     * 联系邮箱
     */
    private String email;

    /**
     * 其他内容访问地址
     */
    private String otherHomepage;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}