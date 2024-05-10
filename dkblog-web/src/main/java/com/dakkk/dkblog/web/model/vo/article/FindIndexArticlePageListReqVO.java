package com.dakkk.dkblog.web.model.vo.article;

import com.dakkk.dkblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * ClassName: FindIndexArticlePageListReqVO
 * Package: com.dakkk.dkblog.web.model.vo.article
 *
 * @Create 2024/5/10 10:36
 * @Author dakkk
 * Description:
 */
@Data
@Builder
@ApiModel("首页查询文章分页数据接口的入参VO")
public class FindIndexArticlePageListReqVO extends BasePageQuery {
}
