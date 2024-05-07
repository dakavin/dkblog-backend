package com.dakkk.dkblog.admin.model.vo.blogsettings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: FindBlogSettingsRspVO
 * Package: com.dakkk.dkblog.admin.model.vo.blogsettings
 *
 * @Create 2024/5/7 18:07
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindBlogSettingsRspVO {

    private String logo;

    private String name;

    private String author;

    private String introduction;

    private String avatar;

    private String githubHomepage;

    private String otherHomepage;

    private String giteeHomepage;

    private String email;
}
