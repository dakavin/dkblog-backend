package com.dakkk.dkblog.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: EditCategoryReqVO
 * Package: com.dakkk.dkblog.admin.model.vo.category
 *
 * @Create 2024/4/28 3:22
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("修改标签接口的入参VO")
public class UpdateTagReqVO {
    /**
     * 标签id
     */
    private Long id;
    /**
     * 标签名称
     */
    @NotBlank(message = "分类名称不能为空")
    @Length(min = 1, max = 20, message = "分类名称字数限制 1~20 之间")
    private String name;
    /**
     * 标签描述
     */
    @Length(max = 100, message = "分类描述字数限制 最大为100")
    private String description;
}
