package com.dakkk.dkblog.web.service;

import com.dakkk.dkblog.common.utils.Response;

/**
 * ClassName: BlogSettingsService
 * Package: com.dakkk.dkblog.web.service
 *
 * @Create 2024/5/10 17:13
 * @Author dakkk
 * Description:
 */
public interface BlogSettingsService {
    /**
     * 获取博客设置详细信息
     */
    Response findDetail();
}
