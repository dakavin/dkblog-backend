package com.dakkk.dkblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dakkk.dkblog.admin.model.vo.category.*;
import com.dakkk.dkblog.admin.service.AdminCategoryService;
import com.dakkk.dkblog.common.domain.dos.CategoryDO;
import com.dakkk.dkblog.common.domain.mapper.CategoryMapper;
import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.exception.BizException;
import com.dakkk.dkblog.common.model.vo.SelectRspVO;
import com.dakkk.dkblog.common.utils.PageResponse;
import com.dakkk.dkblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
     *
     * @param addCategoryReqVO 入参（增加分类的name）
     * @return 返回Response对象，只需要success即可
     */
    @Override
    public Response addCategory(AddCategoryReqVO addCategoryReqVO) {
        String categoryName = addCategoryReqVO.getName();
        String categoryDesc = addCategoryReqVO.getDescription();

        // 先判断该分类是否已经存在
        CategoryDO categoryDO = categoryMapper.selectByName(categoryName);

        if (Objects.nonNull(categoryDO)) {
            log.warn("分类名称：{}，此类已存在", categoryName);
            throw new BizException(ResponseErrorCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }

        // 构建DO类
        CategoryDO insertCategoryDo = CategoryDO.builder()
                .name(categoryName.trim())
                // 描述可以为空
                .description(StringUtils.isNotBlank(categoryDesc) ? categoryDesc : "暂时没有描述")
                .build();

        // 执行 insert
        categoryMapper.insert(insertCategoryDo);

        return Response.success();
    }

    /**
     * 获取分类分页后的数据集合
     *
     * @param findCategoryPageListReqVO 入参（current、size、name，startDate、endDate）
     * @return 出参（PageResponse对象，其中date数据为id、createTime、name的list集合）
     */
    @Override
    public PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO) {
        // 获取当前页，以及每页需要展示的数据数量
        Long current = findCategoryPageListReqVO.getCurrent();
        Long size = findCategoryPageListReqVO.getSize();

        // 分页对象（查询第几页，每页多少数据）
        Page<CategoryDO> page = new Page<>(current, size);

        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> lqw = new LambdaQueryWrapper<>();

        String name = findCategoryPageListReqVO.getName();
        LocalDateTime startDate = findCategoryPageListReqVO.getStartDate();
        LocalDateTime endDate = findCategoryPageListReqVO.getEndDate();

        lqw
                // like 模块查询
                .like(StringUtils.isNotBlank(name), CategoryDO::getName, name.trim())
                // 创建日期 大于等于 起始日期
                .ge(Objects.nonNull(startDate), CategoryDO::getCreateTime, startDate)
                // 创建日期 小于等于 结束日期
                .le(Objects.nonNull(endDate), CategoryDO::getCreateTime, endDate)
                // 按照创建日期降序排列
                .orderByDesc(CategoryDO::getCreateTime);

        // 执行分页查询
        Page<CategoryDO> categoryDOPage = categoryMapper.selectPage(page, lqw);
        List<CategoryDO> categoryDOS = categoryDOPage.getRecords();

        // DO 转 VO
        List<FindCategoryPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(categoryDOS)) {
            vos = categoryDOS.stream()
                    .map(categoryDO -> FindCategoryPageListRspVO.builder()
                            .id(categoryDO.getId())
                            .name(categoryDO.getName())
                            .description(categoryDO.getDescription())
                            .createTime(categoryDO.getCreateTime())
                            .updateTime(categoryDO.getUpdateTime())
                            .build())
                    .collect(Collectors.toList());
        }
        return PageResponse.success(categoryDOPage, vos);
    }

    /**
     * 删除分类的方法
     *
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

    /**
     * 创建文章时，在文章分类的下拉列表中展示分类的 文字描述 和 id
     *
     * @return
     */
    @Override
    public Response findCategorySelectList() {
        // 查询所有分类
        List<CategoryDO> categoryDOS = categoryMapper.selectList(null);

        // DO 转 VO
        List<SelectRspVO> selectRspVOS = null;
        // 如果分类数据不为空
        if (!CollectionUtils.isEmpty(categoryDOS)) {
            selectRspVOS = categoryDOS.stream()
                    .map(categoryDO -> SelectRspVO.builder()
                            .label(categoryDO.getName())
                            .value(categoryDO.getId())
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(selectRspVOS);
    }

    /**
     * 修改分类的名称和描述
     *
     * @param editCategoryReqVO
     * @return
     */
    @Override
    public Response updateCategory(UpdateCategoryReqVO updateCategoryReqVO) {
        Long id = updateCategoryReqVO.getId();
        String categoryName = updateCategoryReqVO.getName().trim();
        String categoryDesc = updateCategoryReqVO.getDescription();

        // 目前修改的数据
        CategoryDO categoryDOById = categoryMapper.selectById(id);
        // 如果修改名称，查看数据库是否有同名的数据
        CategoryDO categoryDOByName = categoryMapper.selectByName(categoryName);

        // 先判断用户是否没有修改
        if (StringUtils.equals(categoryName,categoryDOById.getName()) &&
        StringUtils.equals(categoryDesc,categoryDOById.getDescription())){
            return Response.fail("用户未做任何修改！");
        }

        // 再判断该分类名称是否已经存在，并且和修改的数据名称不一致
        if (Objects.nonNull(categoryDOByName) && !StringUtils.equals(categoryDOById.getName(),categoryDOByName.getName())) {
            log.warn("分类名称：{}，已存在", categoryName);
            throw new BizException(ResponseErrorCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }


        // 构建DO类
        CategoryDO updateCategoryDo = CategoryDO.builder()
                .id(id)
                .name(categoryName.trim())
                // 描述可以为空
                .description(StringUtils.isNotBlank(categoryDesc) ? categoryDesc : "暂时没有描述")
                // 补充更新的时间
                .updateTime(LocalDateTime.now())
                .build();
        // 执行 update
        categoryMapper.updateById(updateCategoryDo);

        return Response.success();
    }
}
