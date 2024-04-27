package com.dakkk.dkblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dakkk.dkblog.admin.model.vo.category.AddCategoryReqVO;
import com.dakkk.dkblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.dakkk.dkblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.dakkk.dkblog.admin.model.vo.category.FindCategoryPageListRspVO;
import com.dakkk.dkblog.admin.service.AdminCategoryService;
import com.dakkk.dkblog.common.domain.dos.CategoryDO;
import com.dakkk.dkblog.common.domain.mapper.CategoryMapper;
import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.exception.BizException;
import com.dakkk.dkblog.common.utils.PageResponse;
import com.dakkk.dkblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    /**
     * 增加分类的方法
     * @param addCategoryReqVO 入参（增加分类的name）
     * @return 返回Response对象，只需要success即可
     */
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

    /**
     * 获取分类分页后的数据集合
     * @param findCategoryPageListReqVO 入参（current、size、name，startDate、endDate）
     * @return 出参（PageResponse对象，其中date数据为id、createTime、name的list集合）
     */
    @Override
    public PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO) {
        // 获取当前页，以及每页需要展示的数据数量
        Long current = findCategoryPageListReqVO.getCurrent();
        Long size = findCategoryPageListReqVO.getSize();

        // 分页对象（查询第几页，每页多少数据）
        Page<CategoryDO> page = new Page<>(current,size);

        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> lqw = new LambdaQueryWrapper<>();

        String name = findCategoryPageListReqVO.getName();
        LocalDate startDate = findCategoryPageListReqVO.getStartDate();
        LocalDate endDate = findCategoryPageListReqVO.getEndDate();

        lqw
                // like 模块查询
                .like(StringUtils.isNotBlank(name),CategoryDO::getName,name.trim())
                // 创建日期 大于等于 起始日期
                .ge(Objects.nonNull(startDate),CategoryDO::getCreateTime,startDate)
                // 创建日期 小于等于 结束日期
                .le(Objects.nonNull(endDate),CategoryDO::getCreateTime,endDate)
                // 按照创建日期降序排列
                .orderByDesc(CategoryDO::getCreateTime);

        // 执行分页查询
        Page<CategoryDO> categoryDOPage = categoryMapper.selectPage(page, lqw);
        List<CategoryDO> categoryDOS = categoryDOPage.getRecords();

        // DO 转 VO
        List<FindCategoryPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(categoryDOS)){
            vos = categoryDOS.stream()
                    .map(categoryDO -> FindCategoryPageListRspVO.builder()
                            .id(categoryDO.getId())
                            .name(categoryDO.getName())
                            .createTime(categoryDO.getCreateTime())
                            .build())
                    .collect(Collectors.toList());
        }
        return PageResponse.success(categoryDOPage,vos);
    }

    /**
     * 删除分类的方法
     * @param deleteCategoryReqVO 入参（删除的id）
     * @return 返回Response对象即可
     */
    @Override
    public Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO) {
        // 分类Id
        Long categoryId = deleteCategoryReqVO.getId();

        // 删除分类
        categoryMapper.deleteById(categoryId);

        return Response.success();
    }
}
