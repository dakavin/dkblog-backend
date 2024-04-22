package com.dakkk.dkblog.jwt.handler;

import com.dakkk.dkblog.common.enums.ResponseErrorCodeEnum;
import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.jwt.exception.UsernameOrPasswordNullException;
import com.dakkk.dkblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: RestAuthenticaitonFailureHandler
 * Package: com.dakkk.dkblog.jwt.handler
 *
 * @Create 2024/4/22 18:39
 * @Author dakkk
 * Description:
 */
@Component
@Slf4j
public class RestAuthenticaitonFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.warn("AuthenticationException: ",exception );
        if (exception instanceof UsernameOrPasswordNullException){
            // 用户名或密码为空
            ResultUtil.fail(response, Response.fail(exception.getMessage()));
        }else if(exception instanceof BadCredentialsException){
            // 用户名或密码错误
            ResultUtil.fail(response,Response.fail(ResponseErrorCodeEnum.USERNAME_OR_PWD_ERROR));
        }

        // 登录失败
        ResultUtil.fail(response, Response.fail(ResponseErrorCodeEnum.LOGIN_FAIL));
    }
}
