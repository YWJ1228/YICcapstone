package com.example.YICcapstone.global.error.exception;

public class InvalidValueException extends BusinessException {
    public InvalidValueException() {
        super(ErrorCode.INVALID_INPUT_VALUE);
    }
}
