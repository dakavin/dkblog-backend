package com.dakkk.dkblog.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// 多模块项目中，必需手动指定扫描 com.dakkk.dkblog 包下面的所有类
@ComponentScan("com.dakkk.dkblog.*")
public class DkblogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DkblogWebApplication.class, args);
    }

}
