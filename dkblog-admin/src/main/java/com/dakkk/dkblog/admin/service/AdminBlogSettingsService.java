package com.dakkk.dkblog.admin.service;

import com.dakkk.dkblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.dakkk.dkblog.common.utils.Response;

/**
 * ClassName: AdminBlogSettingsService
 * Package: com.dakkk.dkblog.admin.service
 *
 * @Create 2024/5/7 17:44
 * @Author dakkk
 * Description:
 */
public interface AdminBlogSettingsService {
    /**
     * 更新博客设置信息
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);

    /**
     * 查询博客设置信息
     */
    Response findDetail();
}
