package com.example.YICcapstone.domain.purchase.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class ReviewNotWrittenByMemberException extends BusinessException {
    public ReviewNotWrittenByMemberException() {
        super(ErrorCode.REVIEW_NOT_WRITTEN_BY_MEMBER);
    }
}
