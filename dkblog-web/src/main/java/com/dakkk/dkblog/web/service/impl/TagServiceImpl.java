package com.dakkk.dkblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dakkk.dkblog.common.domain.dos.TagDO;
import com.dakkk.dkblog.common.domain.mapper.TagMapper;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.model.vo.category.FindTagListRspVO;
import com.dakkk.dkblog.web.service.TagService;
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
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;

    /**
     * 获取分类列表
     *
     * @return
     */
    @Override
    public Response findTagList() {
        // 查询所有分类
        List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());
        // DO转VO
        List<FindTagListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream().map(categoryDO -> FindTagListRspVO.builder()
                            .id(categoryDO.getId())
                            .name(categoryDO.getName())
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(vos);
    }
}
