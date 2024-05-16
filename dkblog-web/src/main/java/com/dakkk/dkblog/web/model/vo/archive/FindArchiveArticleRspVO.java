package com.dakkk.dkblog.web.model.vo.archive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * ClassName: FindArchiveArticleRspVO
 * Package: com.dakkk.dkblog.web.model.vo.archive
 *
 * @Create 2024/5/16 11:05
 * @Author dakkk
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindArchiveArticleRspVO {
    private Long id;
    private String cover;
    private String title;
    /**
     * 发布日期
     */
    private LocalDate createDate;
    /**
     * 发布的月份（不需要展示在前端，主要用于月份分组使用）
     */
    private YearMonth createMonth;
}
