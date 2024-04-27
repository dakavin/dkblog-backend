package com.dakkk.dkblog.admin.model.vo.category;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: AddCategoryReqVO
 * Package: com.dakkk.dkblog.admin.model.vo.category
 *
 * @Create 2024/4/27 14:11
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("添加分类 VO")
public class AddCategoryReqVO {
    @NotBlank(message = "分类名称不能为空")
    @Length(min = 1, max = 10, message = "分类名称字数限制 1~10 之间")
    private String name;
}
