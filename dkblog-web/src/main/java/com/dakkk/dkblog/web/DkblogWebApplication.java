package com.dakkk.dkblog.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.dakkk.dkblog")
public class DkblogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DkblogWebApplication.class, args);
    }

}
