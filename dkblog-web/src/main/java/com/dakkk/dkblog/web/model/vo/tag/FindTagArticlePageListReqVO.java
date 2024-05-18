package com.dakkk.dkblog.web.model.vo.tag;

import com.dakkk.dkblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * ClassName: FindTagArticlePageListReqVO
 * Package: com.dakkk.dkblog.web.model.vo.tag
 *
 * @Create 2024/5/18 20:19
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("首页标签页面下，查询标签对应的文章集合接口的入参VO")
public class FindTagArticlePageListReqVO extends BasePageQuery {
    /**
     * 标签ID
     */
    @NotNull(message = "标签 ID 不能为空")
    private Long id;
}
