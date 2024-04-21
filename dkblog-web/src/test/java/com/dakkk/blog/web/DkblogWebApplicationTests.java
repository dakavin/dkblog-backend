package com.dakkk.blog.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DkblogWebApplicationTests {

    @Test
    public void testLog(){
        log.info("这是一行 info 级别的日志");
        log.warn("这是一行 warn 级别的日志");
        log.error("这是一行 error 级别的日志");

        // 占位符测试
        String author = "dakkk";
        log.info("这是一行带有占位符日志，作者:{}",author);
    }
}
