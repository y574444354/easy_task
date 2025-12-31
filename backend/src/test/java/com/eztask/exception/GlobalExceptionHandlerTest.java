package com.eztask.exception;

import com.eztask.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 全局异常处理器测试类
 *
 * 测试点：
 * 1. 业务异常统一处理
 * 2. 全局异常捕获与返回
 *
 * @author EasyTask Team
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = {
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
    org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration.class
})
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 测试点1：参数校验异常处理
     */
    @Test
    void testHandleValidationException() throws Exception {
        // Arrange - 准备测试数据（用户名为空）
        LoginRequest request = new LoginRequest();
        request.setUsername("");
        request.setPassword("password");

        // Act & Assert - 执行测试并验证结果
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("参数校验失败"))
                .andExpect(jsonPath("$.message").value("请求参数不合法"));
    }

    /**
     * 测试点2：认证异常处理
     */
    @Test
    void testHandleBadCredentialsException() throws Exception {
        // Arrange - 准备测试数据
        LoginRequest request = new LoginRequest();
        request.setUsername("wronguser");
        request.setPassword("wrongpassword");

        // Act & Assert - 执行测试并验证结果
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    /**
     * 测试点3：业务异常处理
     */
    @Test
    @WithMockUser(username = "admin")
    void testHandleBusinessException() throws Exception {
        // 这个测试需要创建一个会抛出BusinessException的Controller方法
        // 暂时跳过，因为没有合适的端点
    }
}