package com.dakkk.dkblog.web.model;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * ClassName: User
 * Package: com.dakkk.blog.web.model
 *
 * @Create 2024/4/21 20:10
 * @Author dakkk
 * Description:
 */
@Data
public class User {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotNull(message = "年龄不能为空")
    @Min(value = 10, message = "年龄要求>=10")
    @Max(value = 100, message = "年龄要求<=100")
    private Integer age;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确！")
    private String email;

    // 创建时间
    private LocalDateTime createTime;
    // 更新日期
    private LocalDate updateTime;
    // 时间
    private LocalTime time;
}
