package com.example.YICcapstone.domain.review.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class PurchaseNotExistException extends BusinessException {
    public PurchaseNotExistException() {
        super(ErrorCode.HANDLE_ACCESS_DENIED); //:TODO ErrorCode 수정
    }
}
