package com.dakkk.dkblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dakkk.dkblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.dakkk.dkblog.admin.service.AdminBlogSettingsService;
import com.dakkk.dkblog.common.domain.dos.BlogSettingsDO;
import com.dakkk.dkblog.common.domain.mapper.BlogSettingsMapper;
import com.dakkk.dkblog.common.utils.Response;
import org.springframework.stereotype.Service;

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
    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        // VO 转 DO
        BlogSettingsDO blogSettingsDO = BlogSettingsDO.builder()
                .id(1L)
                .logo(updateBlogSettingsReqVO.getLogo())
                .name(updateBlogSettingsReqVO.getName())
                .author(updateBlogSettingsReqVO.getAuthor())
                .introduction(updateBlogSettingsReqVO.getIntroduction())
                .avatar(updateBlogSettingsReqVO.getAvatar())
                .githubHomepage(updateBlogSettingsReqVO.getGithubHomepage())
                .giteeHomepage(updateBlogSettingsReqVO.getGiteeHomepage())
                .email(updateBlogSettingsReqVO.getEmail())
                .otherHomepage(updateBlogSettingsReqVO.getOtherHomePage())
                .build();

        // 保存更新（当数据库存在id=1的数据时，实现更新操作，否则实现插入操作）
        saveOrUpdate(blogSettingsDO);
        return Response.success();
    }
}
