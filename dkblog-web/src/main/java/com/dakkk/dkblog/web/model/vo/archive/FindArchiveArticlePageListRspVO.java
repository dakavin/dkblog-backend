package com.dakkk.dkblog.web.model.vo.archive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.List;

/**
 * ClassName: FindArchiveArticlePageListRspVO
 * Package: com.dakkk.dkblog.web.model.vo.archive
 *
 * @Create 2024/5/16 11:06
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArchiveArticlePageListRspVO {
    /**
     * 当前归档的月份
     */
    private YearMonth month;
    private List<FindArchiveArticleRspVO> articles;
}
