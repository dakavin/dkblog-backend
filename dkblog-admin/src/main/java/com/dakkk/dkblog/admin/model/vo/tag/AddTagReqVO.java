package com.dakkk.dkblog.admin.model.vo.tag;

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
@ApiModel("添加标签接口的入参VO")
public class AddTagReqVO {
    @NotBlank(message = "标签名称不能为空")
    @Length(min = 1, max = 20, message = "标签名称字数限制 1~20 之间")
    private String name;

    @Length(max = 100, message = "标签描述字数限制 最大为100")
    private String description;
}
