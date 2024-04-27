package com.dakkk.dkblog.common.model;

import lombok.Data;

/**
 * ClassName: BasePageQuery
 * Package: com.dakkk.dkblog.common.model
 *
 * @Create 2024/4/27 14:45
 * @Author dakkk
 * Description: 用于前端分页入参的通用类（一般会有当前页码和每页数据量）
 */
@Data
public class BasePageQuery {
    /**
     * 当前页码，默认为第一页
     */
    private Long current = 1L;
    /**
     * 每页展示的数据数量，默认为10
     */
    private Long size = 10L;
}
