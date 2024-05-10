package com.dakkk.dkblog.web.model.vo.blogsettings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: FindBlogSettingsDetailRspVO
 * Package: com.dakkk.dkblog.web.model.vo.blogsettings
 *
 * @Create 2024/5/10 17:12
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindBlogSettingsDetailRspVO {
    private String logo;
    private String name;
    private String author;
    private String introduction;
    private String avatar;
    private String githubHomepage;
    private String giteeHomepage;
    private String email;
    private String otherHomepage;
}
