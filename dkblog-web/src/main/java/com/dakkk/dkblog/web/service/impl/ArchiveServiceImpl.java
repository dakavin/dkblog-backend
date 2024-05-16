package com.dakkk.dkblog.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dakkk.dkblog.common.domain.dos.ArticleDO;
import com.dakkk.dkblog.common.domain.mapper.ArticleMapper;
import com.dakkk.dkblog.common.utils.PageResponse;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.web.convert.ArticleConvert;
import com.dakkk.dkblog.web.model.vo.archive.FindArchiveArticlePageListReqVO;
import com.dakkk.dkblog.web.model.vo.archive.FindArchiveArticlePageListRspVO;
import com.dakkk.dkblog.web.model.vo.archive.FindArchiveArticleRspVO;
import com.dakkk.dkblog.web.service.ArchiveService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName: ArchiveServiceImpl
 * Package: com.dakkk.dkblog.web.service.impl
 *
 * @Create 2024/5/16 11:10
 * @Author dakkk
 * Description:
 */
@Service
@Slf4j
public class ArchiveServiceImpl implements ArchiveService {
    @Resource
    private ArticleMapper articleMapper;
    @Override
    public Response findArchivePageList(FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO) {
        Long current = findArchiveArticlePageListReqVO.getCurrent();
        Long size = findArchiveArticlePageListReqVO.getSize();

        // 分页查询
        IPage<ArticleDO> page = articleMapper.selectPageList(current, size, null, null, null);
        List<ArticleDO> articleDOS = page.getRecords();

        List<FindArchiveArticlePageListRspVO> vos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(articleDOS)){
            // DO 转 VO
            List<FindArchiveArticleRspVO> archiveArticleRspVOS = articleDOS.stream()
                    .map(articleDO -> ArticleConvert.INSTANCE.convertDO2ArchiveArticleVO(articleDO))
                    .collect(Collectors.toList());

            // 按创建的月份进行分组
            Map<YearMonth, List<FindArchiveArticleRspVO>> map
                    = archiveArticleRspVOS.stream().collect(Collectors.groupingBy(FindArchiveArticleRspVO::getCreateMonth));

            // 使用 TreeMap 按月份倒序排列
            Map<YearMonth, List<FindArchiveArticleRspVO>> sortedMap = new TreeMap<>(Collections.reverseOrder());
            sortedMap.putAll(map);

            // 遍历排序后的 Map，将其转换为归档 VO
            sortedMap.forEach((k,v)->
                    vos.add(FindArchiveArticlePageListRspVO.builder().month(k).articles(v).build()));
        }
        return PageResponse.success(page,vos);
    }
}
