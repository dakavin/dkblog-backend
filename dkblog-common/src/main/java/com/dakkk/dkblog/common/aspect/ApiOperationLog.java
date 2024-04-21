package com.dakkk.dkblog.common.aspect;

import java.lang.annotation.*;

/**
 * ClassName: ApiOperationLog
 * Package: com.dakkk.dkblog.common.aspect
 *
 * @Create 2024/4/21 19:47
 * @Author dakkk
 * Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ApiOperationLog {
    /**
     * API 功能描述
     * @return 返回API功能描述
     */
    String description() default "";
}
