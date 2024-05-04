package com.dakkk.dkblog.common.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: InsertBatchMapper
 * Package: com.dakkk.dkblog.common.config
 *
 * @Create 2024/5/4 13:44
 * @Author dakkk
 * Description:
 */
public interface InsertBatchMapper<T> extends BaseMapper<T> {
    // 批量插入方法
    int insertBatchSomeColumn(@Param("list") List<T> batchList);
}
