package com.nextnetflix.commonservice.exception;

import org.springframework.http.HttpStatus;

public enum CommonErrorCode implements ErrorCode {

    VALIDATION_ERROR("COM-001", "Validation failed", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("COM-002", "Unauthorized", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("COM-003", "Access denied", HttpStatus.FORBIDDEN),
    NOT_FOUND("COM-004", "Resource not found", HttpStatus.NOT_FOUND),
    INTERNAL_ERROR("COM-500", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;

    CommonErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus status() {
        return status;
    }
}