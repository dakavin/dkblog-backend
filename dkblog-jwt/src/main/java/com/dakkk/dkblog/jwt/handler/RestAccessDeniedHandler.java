package com.dakkk.dkblog.jwt.handler;

import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: RestAccessDeniedHandler
 * Package: com.dakkk.dkblog.jwt.handler
 *
 * @Create 2024/4/22 22:57
 * @Author dakkk
 * Description: 登录成功访问收保护的资源，但是权限不够
 */
@Slf4j
@Component
public class RestAccessDeniedHandler  implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("登录成功访问受保护的资源，权限不够：{}",accessDeniedException);
        // todo 预留，后面引入角色的时候用到
        ResultUtil.fail(response, Response.fail(ResponseErrorCodeEnum.FORBIDDEN));
    }
}
