package com.dakkk.dkblog.admin.model.vo.category;

import com.dakkk.dkblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ClassName: FindCategoryPageListReqVO
 * Package: com.dakkk.dkblog.admin.model.vo.category
 *
 * @Create 2024/4/27 14:50
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@Builder
@ApiModel("查询分类分页数据接口的入参VO")
@NoArgsConstructor
public class FindCategoryPageListReqVO extends BasePageQuery {
    /**
     * 分类名称
     */
    private String name;
    /**
     * 创建的起始日期
     */
    private LocalDateTime startDate;
    /**
     * 创建的结束日期
     */
    private LocalDateTime endDate;
}
