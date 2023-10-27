package com.example.YICcapstone.domain.member.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class MemberPasswordIncorrectedException extends BusinessException {
    public MemberPasswordIncorrectedException() {
        super(ErrorCode.MEMBER_PASSWORD_INCORRETED);
    }
}
