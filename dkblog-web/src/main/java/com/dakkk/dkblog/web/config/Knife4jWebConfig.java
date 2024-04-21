package com.dakkk.dkblog.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * ClassName: Knife4jConfig
 * Package: com.dakkk.dkblog.web.config
 *
 * @Create 2024/4/21 21:53
 * @Author dakkk
 * Description:
 */
@Configuration
@EnableSwagger2WebMvc
@Profile("dev")
public class Knife4jWebConfig {
    @Bean("webApi")
    public Docket createApiDoc(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .groupName("Web 前台接口")
                .select().apis(RequestHandlerSelectors.basePackage("com.dakkk.dkblog.web.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 构建 API 信息
     */
    private ApiInfo buildApiInfo(){
        return new ApiInfoBuilder()
                // 标题
                .title("DK blog 博客前台接口文档")
                // 描述
                .description("DK blog 是一款由 Spring Boot + Vue 3.2 + Vite 4.3 开发的前后端分离博客。")
                // API 服务条款
                . termsOfServiceUrl("https://gitee.com/Dakkk_mike")
                // 联系人
                . contact(new Contact("dakkk","https://gitee.com/Dakkk_mike","mikeylay@126.com"))
                // 版本号
                .version("0.0.1")
                .build();
    }
}
