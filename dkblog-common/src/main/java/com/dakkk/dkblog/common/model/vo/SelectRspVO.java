package com.dakkk.dkblog.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: SelectRspVO
 * Package: com.dakkk.dkblog.common.model.vo
 *
 * @Create 2024/4/27 16:57
 * @Author dakkk
 * Description: 创建文章的时候，下拉列表选择文章的分类时，文章分类的返参
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectRspVO {
    /**
     * 下拉列表展示的文字
     */
    private String label;
    /**
     * 下拉列表的value值，如分类的ID等
     */
    private Object value;
}
