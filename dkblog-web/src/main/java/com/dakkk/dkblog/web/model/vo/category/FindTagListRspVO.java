package com.dakkk.dkblog.web.model.vo.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: FindCategoryListRspVO
 * Package: com.dakkk.dkblog.web.model.vo.category
 *
 * @Create 2024/5/10 10:37
 * @Author dakkk
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindTagListRspVO {
    private Long id;
    private String name;
}
