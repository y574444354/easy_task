package com.eztask.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 *
 * @author EasyTask Team
 * @version 1.0.0
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Profile({"dev", "test"})
public class SwaggerConfig {

    /**
     * 创建API文档
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eztask.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("EasyTask API 文档")
                .description("EasyTask 后端接口文档")
                .contact(new Contact("EasyTask Team", "", ""))
                .version("1.0.0")
                .build();
    }
}