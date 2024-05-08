package com.dakkk.dkblog.admin.service.impl;

import com.dakkk.dkblog.admin.model.vo.file.UploadFileRspVO;
import com.dakkk.dkblog.admin.service.AdminFileService;
import com.dakkk.dkblog.admin.utils.MinioUtil;
import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.exception.BizException;
import com.dakkk.dkblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * ClassName: AdminFileServiceImpl
 * Package: com.dakkk.dkblog.admin.service.impl
 *
 * @Create 2024/5/7 17:04
 * @Author dakkk
 * Description:
 */
@Service
@Slf4j
public class AdminFileServiceImpl implements AdminFileService {
    @Resource
    MinioUtil minioUtil;

    @Override
    public Response uploadFile(MultipartFile file) {
        try {
            // 上传文件
            String url = minioUtil.uploadFile(file);
            // 构建成功返参，将图片的访问链接返回
            return Response.success(UploadFileRspVO.builder().url(url).build());
        } catch (Exception e) {
            log.error("==>上传文件至 Minio 错误：", e);
            throw new BizException(ResponseErrorCodeEnum.FILE_UPLOAD_FAILED);
        }
    }
}
