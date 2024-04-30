package com.dakkk.dkblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dakkk.dkblog.admin.model.vo.category.UpdateCategoryReqVO;
import com.dakkk.dkblog.admin.model.vo.tag.*;
import com.dakkk.dkblog.admin.service.AdminTagService;
import com.dakkk.dkblog.common.domain.dos.CategoryDO;
import com.dakkk.dkblog.common.domain.dos.TagDO;
import com.dakkk.dkblog.common.domain.mapper.TagMapper;
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
 * ClassName: AdminTagServiceImpl
 * Package: com.dakkk.dkblog.admin.service.impl
 *
 * @Create 2024/4/27 14:16
 * @Author dakkk
 * Description:
 */
@Service
@Slf4j
public class AdminTagServiceImpl implements AdminTagService {
    @Resource
    private TagMapper tagMapper;

    /**
     * 增加分类的方法
     *
     * @param addTagReqVO 入参（增加分类的name）
     * @return 返回Response对象，只需要success即可
     */
    @Override
    public Response addTag(AddTagReqVO addTagReqVO) {
        String tagName = addTagReqVO.getName();
        String tagDesc = addTagReqVO.getDescription();

        // 先判断该分类是否已经存在
        TagDO tagDO = tagMapper.selectByName(tagName);

        if (Objects.nonNull(tagDO)) {
            log.warn("标签名称：{}，此标签已存在", tagName);
            throw new BizException(ResponseErrorCodeEnum.TAG_NAME_IS_EXISTED);
        }

        // 构建DO类
        TagDO insertTagDo = TagDO.builder()
                .name(tagName.trim())
                // 描述可以为空
                .description(StringUtils.isNotBlank(tagDesc) ? tagDesc : "暂时没有描述")
                .build();

        // 执行 insert
        tagMapper.insert(insertTagDo);

        return Response.success();
    }

    /**
     * 获取分类分页后的数据集合
     *
     * @param findCategoryPageListReqVO 入参（current、size、name，startDate、endDate）
     * @return 出参（PageResponse对象，其中date数据为id、createTime、name的list集合）
     */
    @Override
    public PageResponse findTagList(FindTagPageListReqVO findTagPageListReqVO) {
        // 获取当前页，以及每页需要展示的数据数量
        Long current = findTagPageListReqVO.getCurrent();
        Long size = findTagPageListReqVO.getSize();

        // 分页对象（查询第几页，每页多少数据）
        Page<TagDO> page = new Page<>(current, size);

        // 构建查询条件
        LambdaQueryWrapper<TagDO> lqw = new LambdaQueryWrapper<>();

        String name = findTagPageListReqVO.getName();
        LocalDateTime startDate = findTagPageListReqVO.getStartDate();
        LocalDateTime endDate = findTagPageListReqVO.getEndDate();

        lqw
                // like 模块查询
                .like(StringUtils.isNotBlank(name), TagDO::getName, name.trim())
                // 创建日期 大于等于 起始日期
                .ge(Objects.nonNull(startDate), TagDO::getCreateTime, startDate)
                // 创建日期 小于等于 结束日期
                .le(Objects.nonNull(endDate), TagDO::getCreateTime, endDate)
                // 按照创建日期降序排列
                .orderByDesc(TagDO::getCreateTime);

        // 执行分页查询
        Page<TagDO> tagDOPage = tagMapper.selectPage(page, lqw);
        List<TagDO> tagDOS = tagDOPage.getRecords();

        // DO 转 VO
        List<FindTagPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> FindTagPageListRspVO.builder()
                            .id(tagDO.getId())
                            .name(tagDO.getName())
                            .description(tagDO.getDescription())
                            .createTime(tagDO.getCreateTime())
                            .updateTime(tagDO.getUpdateTime())
                            .build())
                    .collect(Collectors.toList());
        }
        return PageResponse.success(tagDOPage, vos);
    }

    /**
     * 删除分类的方法
     *
     * @param deleteCategoryReqVO 入参（删除的id）
     * @return 返回Response对象即可
     */
    @Override
    public Response deleteTag(DeleteTagReqVO deleteTagReqVO) {
        // 分类Id
        Long tagId = deleteTagReqVO.getId();

        // 删除分类
        tagMapper.deleteById(tagId);

        return Response.success();
    }

    /**
     * 创建文章时，在文章分类的下拉列表中展示分类的 文字描述 和 id
     *
     * @return
     */
    @Override
    public Response findTagSelectList() {
        // 查询所有分类
        List<TagDO> tagDOS = tagMapper.selectList(null);

        // DO 转 VO
        List<SelectRspVO> selectRspVOS = null;
        // 如果分类数据不为空
        if (!CollectionUtils.isEmpty(tagDOS)) {
            selectRspVOS = tagDOS.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .label(tagDO.getName())
                            .value(tagDO.getId())
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
    public Response updateTag(UpdateTagReqVO updateTagReqVO) {
        Long id = updateTagReqVO.getId();
        String tagName = updateTagReqVO.getName().trim();
        String tagDesc = updateTagReqVO.getDescription();

        // 目前修改的数据
        TagDO tagDOById = tagMapper.selectById(id);
        // 如果修改名称，查看数据库是否有同名的数据
        TagDO tagDOByName = tagMapper.selectByName(tagName);

        // 先判断用户是否没有修改
        if (StringUtils.equals(tagName,tagDOById.getName()) &&
        StringUtils.equals(tagDesc,tagDOById.getDescription())){
            return Response.fail("用户未做任何修改！");
        }

        // 再判断该名称是否已经存在，并且和修改的数据名称不一致
        if (Objects.nonNull(tagDOByName) && !StringUtils.equals(tagDOById.getName(),tagDOByName.getName())) {
            log.warn("分类名称：{}，已存在", tagName);
            throw new BizException(ResponseErrorCodeEnum.TAG_NAME_IS_EXISTED);
        }


        // 构建DO类
        TagDO updateTagDo = TagDO.builder()
                .id(id)
                .name(tagName.trim())
                // 描述可以为空
                .description(StringUtils.isNotBlank(tagDesc) ? tagDesc : "暂时没有描述")
                // 补充更新的时间
                .updateTime(LocalDateTime.now())
                .build();
        // 执行 update
        tagMapper.updateById(updateTagDo);

        return Response.success();
    }
}
