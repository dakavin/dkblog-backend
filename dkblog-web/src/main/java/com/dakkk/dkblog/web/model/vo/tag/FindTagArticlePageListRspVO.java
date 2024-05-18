package com.dakkk.dkblog.web.model.vo.tag;

import com.dakkk.dkblog.common.model.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * ClassName: FindTagArticlePageListRspVO
 * Package: com.dakkk.dkblog.web.model.vo.tag
 *
 * @Create 2024/5/18 20:20
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindTagArticlePageListRspVO{
    private Long id;
    private String cover;
    private String title;
    /**
     * 发布日期
     */
    private LocalDate createDate;
}
