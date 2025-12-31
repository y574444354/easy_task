package com.eztask.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 *
 * @author EasyTask Team
 * @version 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        log.error("参数校验失败: request={}, errors={}", request.getRequestURI(), errors);

        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(System.currentTimeMillis());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError("参数校验失败");
        response.setMessage("请求参数不合法");
        response.setPath(request.getRequestURI());
        response.setValidationErrors(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * 处理认证异常
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
            BadCredentialsException ex,
            HttpServletRequest request) {

        log.error("认证失败: request={}, error={}", request.getRequestURI(), ex.getMessage());

        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(System.currentTimeMillis());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setError("认证失败");
        response.setMessage("用户名或密码错误");
        response.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    /**
     * 处理权限异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(
            AccessDeniedException ex,
            HttpServletRequest request) {

        log.error("权限不足: request={}, error={}", request.getRequestURI(), ex.getMessage());

        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(System.currentTimeMillis());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setError("权限不足");
        response.setMessage("您没有访问该资源的权限");
        response.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException ex,
            HttpServletRequest request) {

        log.error("业务异常: request={}, error={}, code={}",
                request.getRequestURI(), ex.getMessage(), ex.getCode());

        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(System.currentTimeMillis());
        response.setStatus(ex.getHttpStatus().value());
        response.setError(ex.getError());
        response.setMessage(ex.getMessage());
        response.setPath(request.getRequestURI());
        response.setCode(ex.getCode());

        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

    /**
     * 处理所有其他异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception ex,
            HttpServletRequest request) {

        log.error("系统异常: request={}, error={}", request.getRequestURI(), ex.getMessage(), ex);

        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(System.currentTimeMillis());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setError("系统异常");
        response.setMessage("服务器内部错误，请稍后重试");
        response.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * 错误响应DTO
     */
    @Data
    public static class ErrorResponse {
        private Long timestamp;

        private Integer status;

        private String error;

        private String message;

        private String path;

        private String code;

        private Map<String, String> validationErrors;
    }
}