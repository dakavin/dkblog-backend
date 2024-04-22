package com.dakkk.dkblog.jwt.utils;

import com.dakkk.dkblog.common.utils.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: ResultUtil
 * Package: com.dakkk.dkblog.jwt.utils
 *
 * @Create 2024/4/22 18:30
 * @Author dakkk
 * Description:
 */
public class ResultUtil {
    /**
     * 返回参数成功
     *
     * @param response
     * @param result
     * @throws IOException
     */
    public static void ok(HttpServletResponse response, Response<?> result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();
        writer.write(objectMapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }

    /**
     * 返回参数失败
     * @param response
     * @param result
     * @throws IOException
     */
    public static void fail(HttpServletResponse response, Response<?> result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();
        writer.write(objectMapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
    /**
     * 失败响参
     * @param response
     * @param status 可指定响应码，如 401 等
     * @param result
     * @throws IOException
     */
    public static void fail(HttpServletResponse response, Response<?> result, int status) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();
        writer.write(objectMapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}
