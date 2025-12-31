package com.eztask.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 业务异常类
 *
 * @author EasyTask Team
 * @version 1.0.0
 */
@Getter
public class BusinessException extends RuntimeException {

    private final String code;
    private final String error;
    private final HttpStatus httpStatus;

    public BusinessException(String message) {
        super(message);
        this.code = "BUSINESS_ERROR";
        this.error = "业务异常";
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.error = "业务异常";
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public BusinessException(String code, String error, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.error = error;
        this.httpStatus = httpStatus;
    }
}