package com.dakkk.dkblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dakkk.dkblog.common.domain.dos.TagDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author mikey
* @description 针对表【t_tag(文章标签表)】的数据库操作Mapper
* @createDate 2024-04-30 10:26:07
* @Entity com.dakkk.dkblog.common.domain.dos.TagDO
*/
public interface TagMapper extends BaseMapper<TagDO> {
    /**
     * 根据tag名称查询标签
     */
    default TagDO selectByName(String tagName){
        // 构建查询条件
        LambdaQueryWrapper<TagDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TagDO::getName,tagName);
        // 执行查询
        return selectOne(lqw);
    }
}




