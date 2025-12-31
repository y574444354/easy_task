package com.eztask.util;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码编码器测试类
 * 用于生成正确的 BCrypt 哈希值
 *
 * @author EasyTask Team
 * @version 1.0.0
 */
public class PasswordEncoderTest {

    @Test
    public void generatePasswordHash() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // 生成 123456 的 BCrypt 哈希值
        String password = "123456";
        String encodedPassword = encoder.encode(password);
        
        System.out.println("原始密码: " + password);
        System.out.println("BCrypt 哈希值: " + encodedPassword);
        
        // 验证密码是否匹配
        boolean matches = encoder.matches(password, encodedPassword);
        System.out.println("密码匹配: " + matches);
    }
}