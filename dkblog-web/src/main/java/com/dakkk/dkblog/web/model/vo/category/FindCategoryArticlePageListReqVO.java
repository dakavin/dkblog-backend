package com.dakkk.dkblog.web.model.vo.category;

import com.dakkk.dkblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * ClassName: FindCategoryArticlePageListReqVO
 * Package: com.dakkk.dkblog.web.model.vo.category
 *
 * @Create 2024/5/18 15:18
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("首页分类页面下，查询分类对应的文章集合接口的入参VO")
public class FindCategoryArticlePageListReqVO extends BasePageQuery {
    /**
     * 分类ID
     */
    @NotNull(message = "分类 ID 不能为空")
    private Long id;
}
