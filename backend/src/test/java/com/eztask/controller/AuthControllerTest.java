package com.eztask.controller;

import com.eztask.config.JwtConfig;
import com.eztask.dto.LoginRequest;
import com.eztask.dto.LoginResponse;
import com.eztask.util.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 认证控制器测试类
 *
 * 测试点：
 * 1. 用户登录成功并获取JWT Token
 * 2. 用户登录失败（密码错误）
 * 3. JWT Token验证通过场景
 *
 * @author EasyTask Team
 */
@WebMvcTest(controllers = AuthController.class, excludeAutoConfiguration = {
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@Import(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private com.eztask.security.UserDetailsServiceImpl userDetailsService;

    @MockBean
    private JwtConfig jwtConfig;

    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("password");
        
        // Mock JwtConfig配置
        when(jwtConfig.getSecret()).thenReturn("EasyTaskSecretKey2024ForJWTTokenGenerationAndValidationAndSecurityPurpose12345678");
        when(jwtConfig.getExpiration()).thenReturn(86400000L);
        when(jwtConfig.getHeader()).thenReturn("Authorization");
        when(jwtConfig.getPrefix()).thenReturn("Bearer");
    }

    /**
     * 测试点1：用户登录成功并获取JWT Token
     */
    @Test
    void testLoginSuccess() throws Exception {
        // Arrange - 准备测试数据
        UserDetails userDetails = new User("admin", "password", new ArrayList<>());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtTokenUtil.generateToken("admin")).thenReturn("test-jwt-token");
        when(jwtTokenUtil.getExpirationDateFromToken(any())).thenReturn(new java.util.Date());

        // Act & Assert - 执行测试并验证结果
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.token").value("test-jwt-token"))
                .andExpect(jsonPath("$.type").value("Bearer"))
                .andExpect(jsonPath("$.username").value("admin"));
    }

    /**
     * 测试点2：用户登录失败（密码错误）
     */
    @Test
    void testLoginFailureWithBadCredentials() throws Exception {
        // Arrange - 准备测试数据
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("用户名或密码错误"));

        // Act & Assert - 执行测试并验证结果
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }

    /**
     * 测试点3：用户登录失败（用户名为空）
     */
    @Test
    void testLoginFailureWithEmptyUsername() throws Exception {
        // Arrange - 准备测试数据
        LoginRequest request = new LoginRequest();
        request.setUsername("");
        request.setPassword("password");

        // Act & Assert - 执行测试并验证结果
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    /**
     * 测试点4：用户登录失败（密码为空）
     */
    @Test
    void testLoginFailureWithEmptyPassword() throws Exception {
        // Arrange - 准备测试数据
        LoginRequest request = new LoginRequest();
        request.setUsername("admin");
        request.setPassword("");

        // Act & Assert - 执行测试并验证结果
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    /**
     * 测试点5：用户登出成功
     */
    @Test
    void testLogoutSuccess() throws Exception {
        // Act & Assert - 执行测试并验证结果
        mockMvc.perform(post("/auth/logout"))
                .andExpect(status().isOk());
    }
}