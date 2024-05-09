package com.dakkk.dkblog.common.enums;

import com.dakkk.dkblog.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: ErrorCodeEnum
 * Package: com.dakkk.dkblog.common.exception
 *
 * @Create 2024/4/21 21:06
 * @Author dakkk
 * Description:
 */
@Getter
@AllArgsConstructor
public enum ResponseErrorCodeEnum implements BaseExceptionInterface {
    // 通用异常状态码
    SYSTEM_ERROR("10000","\uD83D\uDE11出错啦，后台小哥正在努力修复中。。。"),
    // 业务异常状态码
    PRODUCT_NOT_FOUND("20000","\uD83E\uDD2D该业务不存在哈。。。（测试使用）"),
    // 参数异常状态码
    PARAM_NOT_VALID("10001","\uD83E\uDD28填写的参数错误哦"),
    // 用户认证过程中，登录失败
    LOGIN_FAIL("20000", "\uD83D\uDE35登录失败哟"),
    // 用户认证过程中，用户名和密码错误
    USERNAME_OR_PWD_ERROR("20001", "\uD83E\uDDD0用户名或密码错误啦"),
    // 用户未登录，访问受保护的资源
    UNAUTHORIZED("20002", "\uD83E\uDEE4无访问权限，请先登录呀！"),
    // 用户已登录，但是无权访问受保护的资源
    FORBIDDEN("20004", "\uD83D\uDE0E演示账号仅支持查询操作哈！"),
    // 没有找到该用户
    USERNAME_NOT_FOUND("20003","\uD83E\uDD72该用户不存在哦"),
    // 分类已存在
    CATEGORY_NAME_IS_EXISTED("200005","该分类已存在，请勿重复添加！" ),
    // 标签已存在
    TAG_NAME_IS_EXISTED("200006","该标签已存在，请勿重复添加！" ),
    // 发布文章时，对应的标签不存在
    CATEGORY_NOT_EXISTED("200007","提交的分类不存在！" ),
    // 文章不存在
    ARTICLE_NOT_FOUND("20008","文章不存在！" ),
    // 上传文件失败
    FILE_UPLOAD_FAILED("20009","上传文件失败" ),
    // 分类下包含文章，无法删除
    CATEGROY_CAN_NOT_DELETE("20010", "该分类下包含文章，请先删除对应文章，才能删除！"),
    // 标签下包含文章，无法删除
    TAG_CAN_NOT_DELETE("20011", "该标签下包含文章，请先删除对应文章，才能删除！"),
    // 标签不存在
    TAG_NOT_EXISTED("20012","标签不存在，无法删除" );



    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMsg;
}
