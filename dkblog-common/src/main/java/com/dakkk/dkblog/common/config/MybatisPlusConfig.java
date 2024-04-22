package com.dakkk.dkblog.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: MybatisPlusConfig
 * Package: com.dakkk.dkblog.common.config
 *
 * @Create 2024/4/22 15:34
 * @Author dakkk
 * Description:
 */
@Configuration
@MapperScan("com.dakkk.dkblog.common.domain.mapper")
public class MybatisPlusConfig {
}
