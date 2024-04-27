package com.dakkk.dkblog.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * ClassName: PageResponse
 * Package: com.dakkk.dkblog.common.utils
 *
 * @Create 2024/4/27 14:37
 * @Author dakkk
 * Description: 分页响应类
 */
@Data
public class PageResponse<T> extends Response<List<T>> {
    /**
     * 总记录数
     */
    private Long total = 0L;
    /**
     * 每页显示的记录数，默认每页显示10条数据
     */
    private Long size = 10L;
    /**
     * 当前的页码
     */
    private Long current;
    /**
     * 总页数
     */
    private Long pages;

    /**
     * 成功响应带有分页数据的响应类
     * @param page MP自带的分页接口类
     * @param data 分页数据集合
     * @return
     * @param <T>
     */
    public static <T> PageResponse<T> success(IPage page, List<T> data) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setCurrent(Objects.isNull(page) ? 1L : page.getCurrent());
        response.setSize(Objects.isNull(page) ? 10L : page.getSize());
        response.setPages(Objects.isNull(page) ? 0L : page.getPages());
        response.setTotal(Objects.isNull(page) ? 0L : page.getTotal());
        response.setData(data);
        return response;
    }
}
