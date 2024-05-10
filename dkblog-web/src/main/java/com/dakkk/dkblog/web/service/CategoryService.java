package com.dakkk.dkblog.web.service;

import com.dakkk.dkblog.common.utils.Response;

/**
 * ClassName: CategoryService
 * Package: com.dakkk.dkblog.web.service
 *
 * @Create 2024/5/10 16:39
 * @Author dakkk
 * Description:
 */
public interface CategoryService {
    /**
     * 获取分类列表
     */
    Response findCategoryList();
}
