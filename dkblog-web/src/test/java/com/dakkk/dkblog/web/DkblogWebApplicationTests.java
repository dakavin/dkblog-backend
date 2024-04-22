package com.dakkk.dkblog.web;

import com.dakkk.dkblog.common.domain.dos.UserDO;
import com.dakkk.dkblog.common.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest
@Slf4j
class DkblogWebApplicationTests {

    // 测试 dev 和 prod 环境下 日志的输出是否正确！
    @Test
    public void testLog(){
        log.info("这是一行 info 级别的日志");
        log.warn("这是一行 warn 级别的日志");
        log.error("这是一行 error 级别的日志");

        // 占位符测试
        String author = "dakkk";
        log.info("这是一行带有占位符日志，作者:{}",author);
    }
    @Resource
    private UserMapper userMapper;
    @Test
    public void testMP(){
        UserDO userDO = UserDO.builder()
                .username("mikeylay")
                .password("123456")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .isDeleted(0)
                .build();
        userMapper.insert(userDO);
    }

    @Test
    public void testP6spy(){
        UserDO userDO = UserDO.builder()
                .username("mikeylay1")
                .password("123456")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .isDeleted(0)
                .build();
        userMapper.insert(userDO);
    }
}
