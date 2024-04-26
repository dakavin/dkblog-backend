package com.dakkk.dkblog.admin.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: UpdateAdminUserPasswordReqVO
 * Package: com.dakkk.dkblog.admin.model.vo
 *
 * @Create 2024/4/26 22:17
 * @Author dakkk
 * Description: 前端修改admin用户账户和密码的参数接受实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("修改用户密码 VO")
public class UpdateAdminUserPasswordReqVO {
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String username;
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;
}
