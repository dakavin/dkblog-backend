package com.dakkk.dkblog.admin.model.vo.category;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * ClassName: DeleteCategoryReqVO
 * Package: com.dakkk.dkblog.admin.model.vo.category
 *
 * @Create 2024/4/27 16:42
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("删除分类接口的入参VO")
public class DeleteCategoryReqVO {
    @NotNull(message = "分类 ID 不能为空")
    private Long id;
}
