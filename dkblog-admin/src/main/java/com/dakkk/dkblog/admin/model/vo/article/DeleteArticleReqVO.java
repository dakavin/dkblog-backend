package com.dakkk.dkblog.admin.model.vo.article;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * ClassName: DeleteArticleReqVO
 * Package: com.dakkk.dkblog.admin.model.vo.article
 *
 * @Create 2024/5/4 14:43
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("删除文章接口的入参VO")
public class DeleteArticleReqVO {
    @NotNull(message = "文章 ID 不能为空")
    private Long id;
}
