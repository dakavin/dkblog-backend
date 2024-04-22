package com.dakkk.dkblog.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * ClassName: UsernameOrPasswordNullException
 * Package: com.dakkk.dkblog.jwt.exception
 *
 * @Create 2024/4/22 18:18
 * @Author dakkk
 * Description:
 */
public class UsernameOrPasswordNullException extends AuthenticationException {
    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}
