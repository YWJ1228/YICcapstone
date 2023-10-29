package com.example.YICcapstone.domain.review.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class ReviewNotFoundException extends BusinessException {
    public ReviewNotFoundException() {
        super(ErrorCode.HANDLE_ACCESS_DENIED); //:TODO ErrorCode 수정
    }
}
