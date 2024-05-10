package com.dakkk.dkblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dakkk.dkblog.common.domain.dos.CategoryDO;
import com.dakkk.dkblog.common.domain.mapper.CategoryMapper;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.model.vo.category.FindTagListRspVO;
import com.dakkk.dkblog.web.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: CategoryServiceImpl
 * Package: com.dakkk.dkblog.web.service.impl
 *
 * @Create 2024/5/10 16:39
 * @Author dakkk
 * Description:
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 获取分类列表
     *
     * @return
     */
    @Override
    public Response findCategoryList() {
        // 查询所有分类
        List<CategoryDO> categoryDOS = categoryMapper.selectList(Wrappers.emptyWrapper());
        // DO转VO
        List<FindTagListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(categoryDOS)) {
            vos = categoryDOS.stream().map(categoryDO -> FindTagListRspVO.builder()
                            .id(categoryDO.getId())
                            .name(categoryDO.getName())
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(vos);
    }
}
