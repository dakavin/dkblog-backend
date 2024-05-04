package com.dakkk.dkblog.admin.model.vo.article;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ClassName: PublishArticleReqVO
 * Package: com.dakkk.dkblog.admin.model.vo.article
 *
 * @Create 2024/4/30 15:17
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "发布文章接口的入参VO")
public class PublishArticleReqVO {
    @NotBlank(message = "文章标题不能为空")
    @Length(min = 1, max = 40, message = "文章标题字数需要在 1~40 之间")
    private String title;

    @NotBlank(message = "文章内容不能为空")
    private String content;

    @NotBlank(message = "文章方面不能为空")
    private String cover;

    /**
     * 文章摘要（不强制要求）
     */
    private String summary;

    @NotNull(message = "文章分类不能为空")
    private Long categoryId;

    @NotEmpty(message = "文章标签不能为空")
    private List<String> tags;
}
