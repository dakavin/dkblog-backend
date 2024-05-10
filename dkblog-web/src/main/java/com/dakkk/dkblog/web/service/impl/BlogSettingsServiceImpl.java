package com.dakkk.dkblog.web.service.impl;

import com.dakkk.dkblog.admin.convert.BlogSettingsConvert;
import com.dakkk.dkblog.admin.model.vo.blogsettings.FindBlogSettingsRspVO;
import com.dakkk.dkblog.common.domain.dos.BlogSettingsDO;
import com.dakkk.dkblog.common.domain.mapper.BlogSettingsMapper;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.service.BlogSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName: BlogSettingsServiceImpl
 * Package: com.dakkk.dkblog.web.service.impl
 *
 * @Create 2024/5/10 17:13
 * @Author dakkk
 * Description:
 */
@Service
@Slf4j
public class BlogSettingsServiceImpl implements BlogSettingsService {
    @Resource
    private BlogSettingsMapper blogSettingsMapper;
    @Override
    public Response findDetail() {
        // 查询博客设置信息（约定的 ID 为 1）
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);

        // DO 转 VO
        FindBlogSettingsRspVO findBlogSettingsRspVO = BlogSettingsConvert.INSTANCE.convertDO2VO(blogSettingsDO);

        return Response.success(findBlogSettingsRspVO);
    }
}
