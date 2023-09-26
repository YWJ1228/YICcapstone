package com.example.YICcapstone.domain.member.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class MemberUsernameDuplicatedException extends BusinessException {
    public MemberUsernameDuplicatedException() {
        super(ErrorCode.MEMBER_USERNAME_DUPLICATED);
    }
}
