package com.eztask.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AI API配置测试类
 *
 * 测试点：
 * 1. AI API配置正确加载
 *
 * @author EasyTask Team
 */
@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
    org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration.class
})
public class AiClientConfigTest {

    @Autowired
    private AiClientConfig aiClientConfig;

    /**
     * 测试点1：AI API配置正确加载
     */
    @Test
    void testAiClientConfigLoaded() {
        // Act & Assert - 验证配置正确加载
        assertThat(aiClientConfig).isNotNull();
        assertThat(aiClientConfig.getBaseUrl()).isEqualTo("https://api.openai.com/v1");
        assertThat(aiClientConfig.getKey()).isEqualTo("test-api-key");
        assertThat(aiClientConfig.getTimeout()).isEqualTo(30000);
        assertThat(aiClientConfig.getMaxRetries()).isEqualTo(3);
        assertThat(aiClientConfig.getModel()).isEqualTo("gpt-3.5-turbo");
    }
}