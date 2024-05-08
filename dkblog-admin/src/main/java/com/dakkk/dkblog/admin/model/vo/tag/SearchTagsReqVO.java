package com.dakkk.dkblog.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: SearchTagsReqVO
 * Package: com.dakkk.dkblog.admin.model.vo.tag
 *
 * @Create 2024/5/8 23:51
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "标签模糊查询接口的入参VO")
public class SearchTagsReqVO {
    @NotBlank(message = "标签查询关键词不能为空")
    private String key;
}
