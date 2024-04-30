package com.dakkk.dkblog.admin.model.vo.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: FindCategoryPageListRspVO
 * Package: com.dakkk.dkblog.admin.model.vo.category
 *
 * @Create 2024/4/27 15:00
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindTagPageListRspVO {
    /**
     * 标签ID
     */
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
     * 更新时间
     */
    private LocalDateTime updateTime;
}
