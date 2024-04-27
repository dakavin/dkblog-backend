package com.dakkk.dkblog.admin.service;

import com.dakkk.dkblog.admin.model.vo.category.AddCategoryReqVO;
import com.dakkk.dkblog.common.utils.Response;

/**
 * ClassName: AdminCategoryService
 * Package: com.dakkk.dkblog.admin.service
 *
 * @Create 2024/4/27 14:15
 * @Author dakkk
 * Description:
 */
public interface AdminCategoryService {
    /**
     * 添加分类
     */
    Response addCategory(AddCategoryReqVO addCategoryReqVO);
}
