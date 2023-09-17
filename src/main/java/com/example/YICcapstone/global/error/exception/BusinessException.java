package com.example.YICcapstone.global.error.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException() {
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
    }

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
