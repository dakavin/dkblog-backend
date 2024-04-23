package com.dakkk.dkblog.jwt.handler;

import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: RestAuthenticationEntryPoint
 * Package: com.dakkk.dkblog.jwt.handler
 *
 * @Create 2024/4/22 22:44
 * @Author dakkk
 * Description: 处理 用户未登录访问受保护的资源 的情况
 */
@Slf4j
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.warn("用户未登录，访问受保护的资源：",authException);
        if(authException instanceof InsufficientAuthenticationException){
            ResultUtil.fail(response,Response.fail(ResponseErrorCodeEnum.UNAUTHORIZED),HttpStatus.UNAUTHORIZED.value());

        }
        ResultUtil.fail(response,Response.fail(authException.getMessage()),HttpStatus.UNAUTHORIZED.value());
    }
}
