package com.dakkk.dkblog.jwt.filter;

import com.dakkk.dkblog.jwt.utils.JwtTokenHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * ClassName: TokenAuthenticationFilter
 * Package: com.dakkk.dkblog.jwt.filter
 *
 * @Create 2024/4/22 22:24
 * @Author dakkk
 * Description: token校验过滤器
 */
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private JwtTokenHelper jwtTokenHelper;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Value("${jwt.tokenHeaderKey}")
    private String tokenHeaderKey;
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中获取 key 为 Authorization 的值
        String header = request.getHeader(tokenHeaderKey);

        // 判断 value 值是否以 Bearer 开头
        if (StringUtils.startsWith(header, tokenPrefix)) {
            // 截取 token 令牌
            String token = StringUtils.substring(header, 6);
            log.info("Token:{}", token);

            // 判空 Token
            if (StringUtils.isNotBlank(token)) {
                try {
                    // 校验 token 是否可用，若解析异常，针对不同异常作出不同的响应参数
                    jwtTokenHelper.validateToken(token);
                } catch (SignatureException | MalformedJwtException | UnsupportedJwtException |
                         IllegalArgumentException e) {
                    // 抛出异常，统一让 AuthenticationEntryPoint 处理响应参数
                    authenticationEntryPoint.commence(request, response, new AuthenticationServiceException("Token 不可用"));
                    return;
                } catch (ExpiredJwtException e) {
                    authenticationEntryPoint.commence(request, response, new AuthenticationServiceException("Token 已失效"));
                    return;
                }

                // 从token中解析出用户名
                String username = jwtTokenHelper.getUsernameByToken(token);

                if (StringUtils.isNotBlank(username)
                        && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    // 根据用户名获取用户的详细信息
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    // 将用户信息存入  authentication，方便后续校验，一定要给UserDetails权限
                    // ！！！出现了重大bug，因为没有将用户的角色从UserDetails对象中取出！！！，导致无法鉴权用户的角色
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        // 继续执行下一个过滤器
        filterChain.doFilter(request,response);
    }
}
