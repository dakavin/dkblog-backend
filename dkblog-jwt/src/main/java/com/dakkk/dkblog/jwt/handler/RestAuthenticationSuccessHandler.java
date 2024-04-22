package com.dakkk.dkblog.jwt.handler;

import com.dakkk.dkblog.common.utils.Response;
import com.dakkk.dkblog.jwt.model.LoginRspVO;
import com.dakkk.dkblog.jwt.utils.JwtTokenHelper;
import com.dakkk.dkblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: RestAuthenticationSuccessHandler
 * Package: com.dakkk.dkblog.jwt.handler
 *
 * @Create 2024/4/22 18:25
 * @Author dakkk
 * Description:
 */
@Component
@Slf4j
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 从 authentication 对象中获取用户的 UserDetails 实例，这里获取用户的用户名
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 通过用户名生成 token
        String username = userDetails.getUsername();
        String token = jwtTokenHelper.generateToken(username);

        // 将token保存在返参的token属性上
        LoginRspVO loginRspVO = LoginRspVO.builder().token(token).build();

        // 统一响应返参
        ResultUtil.ok(response, Response.success(loginRspVO));
    }
}
