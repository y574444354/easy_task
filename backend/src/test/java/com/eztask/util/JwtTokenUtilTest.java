package com.eztask.util;

import com.eztask.config.JwtConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * JWT令牌工具类测试
 * 
 * 测试点：
 * 1. JWT Token验证通过场景
 * 2. JWT Token生成
 * 3. JWT Token刷新
 *
 * @author EasyTask Team
 */
@ExtendWith(MockitoExtension.class)
public class JwtTokenUtilTest {

    @Mock
    private JwtConfig jwtConfig;

    @InjectMocks
    private JwtTokenUtil jwtTokenUtil;

    private String token;

    @BeforeEach
    void setUp() {
        // 使用64字节的密钥以满足HS512算法的512位要求
        when(jwtConfig.getSecret()).thenReturn("EasyTaskSecretKey2024ForJWTTokenGenerationAndValidationAndSecurityPurpose12345678");
        when(jwtConfig.getExpiration()).thenReturn(86400000L); // 24小时
        
        // 生成测试Token
        token = jwtTokenUtil.generateToken("admin");
    }

    /**
     * 测试点3：JWT Token验证通过场景
     */
    @Test
    void testValidateTokenSuccess() {
        // Act & Assert - 执行测试并验证结果
        assertThat(token).isNotNull();
        assertThat(jwtTokenUtil.validateToken(token, "admin")).isTrue();
    }

    /**
     * 测试点：JWT Token生成
     */
    @Test
    void testGenerateToken() {
        // Act - 执行测试
        String newToken = jwtTokenUtil.generateToken("testuser");

        // Assert - 验证结果
        assertThat(newToken).isNotNull();
        assertThat(jwtTokenUtil.getUsernameFromToken(newToken)).isEqualTo("testuser");
    }

    /**
     * 测试点：JWT Token刷新
     */
    @Test
    void testRefreshToken() {
        // Act - 执行测试
        String refreshedToken = jwtTokenUtil.refreshToken(token);

        // Assert - 验证结果
        assertThat(refreshedToken).isNotNull();
        assertThat(jwtTokenUtil.getUsernameFromToken(refreshedToken)).isEqualTo("admin");
    }

    /**
     * 测试点：从Token获取用户名
     */
    @Test
    void testGetUsernameFromToken() {
        // Act & Assert - 执行测试并验证结果
        String username = jwtTokenUtil.getUsernameFromToken(token);
        assertThat(username).isEqualTo("admin");
    }

    /**
     * 测试点：从Token获取过期时间
     */
    @Test
    void testGetExpirationDateFromToken() {
        // Act - 执行测试
        Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(token);

        // Assert - 验证结果
        assertThat(expirationDate).isNotNull();
        assertThat(expirationDate.after(new Date())).isTrue();
    }

    /**
     * 测试点：Token验证失败（用户名不匹配）
     */
    @Test
    void testValidateTokenWithWrongUsername() {
        // Act & Assert - 执行测试并验证结果
        assertThat(jwtTokenUtil.validateToken(token, "wronguser")).isFalse();
    }
}