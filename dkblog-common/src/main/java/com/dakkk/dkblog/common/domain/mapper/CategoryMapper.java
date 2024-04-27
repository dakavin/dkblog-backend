package com.dakkk.dkblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dakkk.dkblog.common.domain.dos.CategoryDO;
import lombok.Data;


/**
* @author mikey
* @description 针对表【t_category(文章分类表)】的数据库操作Mapper
* @createDate 2024-04-28 02:18:38
* @Entity generator.domain.CategoryDO
*/
public interface CategoryMapper extends BaseMapper<CategoryDO> {
    /**
     * 根据分类名称查询分类
     */
    default CategoryDO selectByName(String categoryName) {
        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CategoryDO::getName, categoryName);
        // 执行查询
        return selectOne(lqw);
    }
}




