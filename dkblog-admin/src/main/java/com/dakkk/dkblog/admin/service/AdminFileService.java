package com.dakkk.dkblog.admin.service;

import com.dakkk.dkblog.common.utils.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: AdminFileService
 * Package: com.dakkk.dkblog.admin.service
 *
 * @Create 2024/5/7 17:03
 * @Author dakkk
 * Description:
 */
public interface AdminFileService {
    /**
     * 上传文件的方法
     */
    Response uploadFile(MultipartFile file);
}
