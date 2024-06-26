package com.dakkk.dkblog.admin.model.vo.user;

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
 * Description: 前端修改admin用户账户和密码的入参
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("修改用户密码接口的入参VO")
public class UpdateAdminUserPasswordReqVO {
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String username;
    @NotBlank(message = "旧密码不能为空")
    @ApiModelProperty("旧密码")
    private String originPassword;
    @NotBlank(message = "新密码不能为空")
    @ApiModelProperty("新密码")
    private String password;
}
