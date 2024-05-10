package com.dakkk.dkblog.web.convert;

import com.dakkk.dkblog.common.domain.dos.BlogSettingsDO;
import com.dakkk.dkblog.web.model.vo.blogsettings.FindBlogSettingsDetailRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ClassName: BlogSettingsConvert
 * Package: com.dakkk.dkblog.web.convert
 *
 * @Create 2024/5/10 17:12
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
     * DO 转 VO 方法
     */
    FindBlogSettingsDetailRspVO convertDO2VO(BlogSettingsDO bean);

}
