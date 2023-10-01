package com.example.YICcapstone.domain.member.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class MemberNicknameDuplicatedException extends BusinessException {
    public MemberNicknameDuplicatedException() {
        super(ErrorCode.MEMBER_NICKNAME_DUPLICATED);
    }
}
