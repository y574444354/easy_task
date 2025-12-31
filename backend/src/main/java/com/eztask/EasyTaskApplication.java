package com.eztask;

import com.eztask.config.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * EasyTask 应用主启动类
 *
 * @author EasyTask Team
 * @version 1.0.0
 */
@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class EasyTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyTaskApplication.class, args);
        System.out.println("========================================");
        System.out.println("EasyTask Backend Application Started!");
        System.out.println("Swagger UI: http://localhost:8080/api/swagger-ui.html");
        System.out.println("Knife4j UI: http://localhost:8080/api/doc.html");
        System.out.println("========================================");
    }
}