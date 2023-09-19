package com.example.YICcapstone.global.error.exception;

public class EntityNotFoundException extends BusinessException {
    private final ErrorCode errorCode;

    public EntityNotFoundException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
