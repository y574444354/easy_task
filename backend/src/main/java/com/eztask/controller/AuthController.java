package com.eztask.controller;

import com.eztask.dto.LoginRequest;
import com.eztask.dto.LoginResponse;
import com.eztask.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 认证控制器
 *
 * @author EasyTask Team
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "认证接口")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private com.eztask.security.UserDetailsServiceImpl userDetailsService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            log.info("用户登录请求: username={}", loginRequest.getUsername());

            // 认证用户
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // 加载用户详情
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 生成JWT Token
            String token = jwtTokenUtil.generateToken(userDetails.getUsername());

            // 返回登录响应
            LoginResponse response = new LoginResponse();
            response.setToken(token);
            response.setType("Bearer");
            response.setUsername(userDetails.getUsername());
            response.setExpiresIn(jwtTokenUtil.getExpirationDateFromToken(token).getTime());

            log.info("用户登录成功: username={}", loginRequest.getUsername());
            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            log.error("用户登录失败: username={}, error=用户名或密码错误", loginRequest.getUsername());
            return ResponseEntity.status(401).body("用户名或密码错误");
        } catch (Exception e) {
            log.error("用户登录失败: username={}, error={}", loginRequest.getUsername(), e.getMessage());
            return ResponseEntity.status(500).body("登录失败: " + e.getMessage());
        }
    }

    /**
     * 刷新Token
     */
    @PostMapping("/refresh")
    @ApiOperation("刷新Token")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.substring(7); // 移除 "Bearer " 前缀
            String newToken = jwtTokenUtil.refreshToken(token);
            
            LoginResponse response = new LoginResponse();
            response.setToken(newToken);
            response.setType("Bearer");
            response.setUsername(jwtTokenUtil.getUsernameFromToken(newToken));
            response.setExpiresIn(jwtTokenUtil.getExpirationDateFromToken(newToken).getTime());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Token刷新失败");
        }
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    @ApiOperation("用户登出")
    public ResponseEntity<?> logout() {
        // JWT是无状态的，客户端删除Token即可
        return ResponseEntity.ok("登出成功");
    }
}