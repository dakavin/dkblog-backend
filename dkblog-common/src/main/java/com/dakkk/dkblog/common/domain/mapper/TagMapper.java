package com.dakkk.dkblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dakkk.dkblog.common.domain.dos.TagDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

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

    /**
     * 根据标签名 模糊查询
     */
    default List<TagDO> selectByKey(String key){
        LambdaQueryWrapper<TagDO> lqw = new LambdaQueryWrapper<>();

        // 构造模糊查询条件
        lqw.like(TagDO::getName,key).orderByDesc(TagDO::getCreateTime);

        return selectList(lqw);
    }

    /**
     * 根据标签 ID 批量查询
     */
    default List<TagDO> selectByIds(List<Long> tagIds){
        return selectList(Wrappers.<TagDO>lambdaQuery()
                .in(TagDO::getId,tagIds));
    }
}




