package com.dakkk.dkblog.admin.service.impl;

import com.baomidou.mybatisplus.core.injector.methods.SelectById;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dakkk.dkblog.admin.convert.BlogSettingsConvert;
import com.dakkk.dkblog.admin.model.vo.blogsettings.FindBlogSettingsRspVO;
import com.dakkk.dkblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.dakkk.dkblog.admin.service.AdminBlogSettingsService;
import com.dakkk.dkblog.common.domain.dos.BlogSettingsDO;
import com.dakkk.dkblog.common.domain.mapper.BlogSettingsMapper;
import com.dakkk.dkblog.common.utils.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName: AdminBlogSettingsServiceImpl
 * Package: com.dakkk.dkblog.admin.service.impl
 *
 * @Create 2024/5/7 17:45
 * @Author dakkk
 * Description:
 */
@Service
public class AdminBlogSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper,BlogSettingsDO>
        implements AdminBlogSettingsService {
    @Resource
    BlogSettingsMapper blogSettingsMapper;

    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        // VO 转 DO
        BlogSettingsDO blogSettingsDO =
                BlogSettingsConvert.INSTANCE.convertVO2DO(updateBlogSettingsReqVO);
        blogSettingsDO.setId(1L);

        // 保存更新（当数据库存在id=1的数据时，实现更新操作，否则实现插入操作）
        saveOrUpdate(blogSettingsDO);
        return Response.success();
    }

    @Override
    public Response findDetail() {
        // 数据库中查询数据
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);
        // DO转VO
        FindBlogSettingsRspVO findBlogSettingsRspVO = BlogSettingsConvert.INSTANCE.convertDO2VO(blogSettingsDO);
        // 返参
        return Response.success(findBlogSettingsRspVO);
    }
}
