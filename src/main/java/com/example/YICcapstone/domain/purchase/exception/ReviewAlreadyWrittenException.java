package com.example.YICcapstone.domain.purchase.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class ReviewAlreadyWrittenException extends BusinessException {
    public ReviewAlreadyWrittenException() {
        super(ErrorCode.REVIEW_ALREADY_WRITTEN);
    }
}
