package com.dakkk.dkblog.admin.model.vo.blogsettings;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: UpdateBlogSettingsReqVO
 * Package: com.dakkk.dkblog.admin.model.vo.blogsettings
 *
 * @Create 2024/5/7 17:41
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("博客基础信息修改接口的入参VO")
public class UpdateBlogSettingsReqVO {
    @NotBlank(message = "博客 LOGO 不能为空")
    private String logo;
    @NotBlank(message = "博客 名称 不能为空")
    private String name;
    @NotBlank(message = "博客 作者 不能为空")
    private String author;
    @NotBlank(message = "博客 介绍语 不能为空")
    private String introduction;
    @NotBlank(message = "博客 头像 不能为空")
    private String avatar;

    private String githubHomepage;
    private String giteeHomepage;
    private String email;
    private String otherHomePage;
}
