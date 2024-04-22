package com.dakkk.dkblog.jwt.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

/**
 * ClassName: JwtTokenHelper
 * Package: com.dakkk.dkblog.jwt.utils
 *
 * @Create 2024/4/22 17:02
 * @Author dakkk
 * Description: jwt token 工具类
 */
@Component
public class JwtTokenHelper implements InitializingBean {
    /**
     * 签发人
     */
    @Value("${jwt.issuer}")
    private String issuer;

    /**
     * 存储签发 JWT 令牌时使用的密钥
     */
    private Key key;

    /**
     * 用于解析 JWT 令牌
     */
    private JwtParser jwtParser;

    /**
     * 接收一个 Base64 编码的密钥字符串
     * 并将其解码为 Key 对象，用于签名和验证 JWT 令牌
     */
    @Value("${jwt.secret}")
    public void setBase64Key(String base64Key){
        key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Key));
    }

    /**
     * 重写InitializingBean的方法，在JwtParser的所有属性被设置之后被调用，执行一些初始化操作
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // 指定令牌解析器的 签发者 和 密钥
        // 考虑到不同服务器之间可能存在时钟偏移，setAllowedClockSkewSeconds
        // 用于设置能够容忍的最大的时钟误差
        jwtParser = Jwts.parserBuilder().requireIssuer(issuer)
                .setSigningKey(key).setAllowedClockSkewSeconds(10)
                .build();
    }

    /**
     * 根据username生成一个 JWT 令牌(token)，该令牌使用 key 进行签名
     * 令牌包含用户名和其他信息，如发行者和失效时间
     */
    public String generateToken(String username){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = now.plusHours(1);

        // 生成令牌
        return Jwts.builder().setSubject(username)
                //设置令牌的发行者
                .setIssuer(issuer)
                // 设置令牌的发行时间
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                // 设置令牌的过期时间
                .setExpiration(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()))
                // 通过密钥对令牌进行签名
                .signWith(key)
                // 完成令牌的构建过程
                .compact();
    }

    /**
     * 解析传入的JWT令牌（token），并验证签名和有效期
     * 返回值：Jws->JWT令牌的轻量级封装，Claims->JWT令牌中包含的声明集合
     */
    public Jws<Claims> parseToken(String token){
        try {
            // 解析令牌，验证令牌签名，并提取令牌的声明
            return jwtParser.parseClaimsJws(token);
        // 签名异常、令牌格式错误、不支持的 JWT 异常或非法参数异常
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e){
            throw new BadCredentialsException("Token 不可用", e);
        // token过期异常
        } catch (ExpiredJwtException e){
            throw new CredentialsExpiredException("Token 失效", e);
        }
    }

    /**
     * 生成一个 Base64 的安全密钥字符串的方法
     */
    private static String generateBase64Key(){
        // 生成安全密钥
        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        // 将密钥进行Base64编码
        String base64Key = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        return base64Key;
    }

    /**
     * 输出一个 Base64 的安全密钥
     */
    public static void main(String[] args) {
        System.out.println("key: " + generateBase64Key());
    }

}
