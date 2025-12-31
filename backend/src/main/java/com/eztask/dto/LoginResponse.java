package com.eztask.dto;

import lombok.Data;

/**
 * 登录响应DTO
 *
 * @author EasyTask Team
 * @version 1.0.0
 */
@Data
public class LoginResponse {

    private String token;

    private String type;

    private String username;

    private Long expiresIn;
}