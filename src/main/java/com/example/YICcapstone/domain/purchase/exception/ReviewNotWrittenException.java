package com.example.YICcapstone.domain.purchase.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class ReviewNotWrittenException extends BusinessException {
    public ReviewNotWrittenException() {
        super(ErrorCode.REVIEW_NOT_WRITTEN);
    }
}
