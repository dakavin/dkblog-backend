package com.dakkk.dkblog.web.model.vo.archive;

import com.dakkk.dkblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * ClassName: FindArchiveArticlePageListReqVO
 * Package: com.dakkk.dkblog.web.model.vo.archive
 *
 * @Create 2024/5/16 11:03
 * @Author dakkk
 * Description:
 */
@Data
@Builder
@ApiModel("文章归档分页接口的入参VO")
public class FindArchiveArticlePageListReqVO extends BasePageQuery {
}
