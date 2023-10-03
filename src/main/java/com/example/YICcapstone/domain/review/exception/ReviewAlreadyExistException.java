package com.example.YICcapstone.domain.review.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class ReviewAlreadyExistException extends BusinessException {
    public ReviewAlreadyExistException() {
        super(ErrorCode.REVIEW_ALREADY_EXIST);
    }
}
