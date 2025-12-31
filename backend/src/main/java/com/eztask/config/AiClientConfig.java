package com.eztask.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * AI API客户端配置类
 *
 * @author EasyTask Team
 * @version 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ai.api")
public class AiClientConfig {

    /**
     * API基础URL
     */
    private String baseUrl;

    /**
     * API密钥
     */
    private String key;

    /**
     * 请求超时时间（毫秒）
     */
    private Integer timeout;

    /**
     * 最大重试次数
     */
    private Integer maxRetries;

    /**
     * 默认模型
     */
    private String model;
}