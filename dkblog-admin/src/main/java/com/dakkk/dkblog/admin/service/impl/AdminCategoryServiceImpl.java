package com.dakkk.dkblog.admin.service.impl;

import com.dakkk.dkblog.admin.model.vo.category.AddCategoryReqVO;
import com.dakkk.dkblog.admin.service.AdminCategoryService;
import com.dakkk.dkblog.common.domain.dos.CategoryDO;
import com.dakkk.dkblog.common.domain.mapper.CategoryMapper;
import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.exception.BizException;
import com.dakkk.dkblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * ClassName: AdminCategoryServiceImpl
 * Package: com.dakkk.dkblog.admin.service.impl
 *
 * @Create 2024/4/27 14:16
 * @Author dakkk
 * Description:
 */
@Service
@Slf4j
public class AdminCategoryServiceImpl implements AdminCategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Response addCategory(AddCategoryReqVO addCategoryReqVO) {
        String categoryName = addCategoryReqVO.getName();

        // 先判断该分类是否已经存在
        CategoryDO categoryDO = categoryMapper.selectByName(categoryName);

        if (Objects.nonNull(categoryDO)) {
            log.warn("分类名称：{}，此类已存在", categoryName);
            throw new BizException(ResponseErrorCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }

        // 构建DO类
        CategoryDO insertCategoryDo = CategoryDO.builder()
                .name(addCategoryReqVO.getName().trim()).build();

        // 执行 insert
        categoryMapper.insert(insertCategoryDo);

        return Response.success();
    }
}
