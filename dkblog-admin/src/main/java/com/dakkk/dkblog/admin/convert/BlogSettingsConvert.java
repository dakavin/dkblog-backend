package com.dakkk.dkblog.admin.convert;

import com.dakkk.dkblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.dakkk.dkblog.common.domain.dos.BlogSettingsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ClassName: BlogSettingsConvert
 * Package: com.dakkk.dkblog.admin.convert
 *
 * @Create 2024/5/7 18:01
 * @Author dakkk
 * Description:
 */
@Mapper
public interface BlogSettingsConvert {
    /**
     * 初始化 convert 实例
     */
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    /**
     * VO 转 DO
     */
    BlogSettingsDO convertVO2DO(UpdateBlogSettingsReqVO bean);
}
