package com.example.YICcapstone.global.error.exception;

public class HandleAccessException extends BusinessException {
    public HandleAccessException() {
        super(ErrorCode.HANDLE_ACCESS_DENIED);
    }
}
