package com.example.YICcapstone.domain.member.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class MemberNotExistException extends BusinessException {
    public MemberNotExistException() {
        super(ErrorCode.MEMBER_NOT_EXIST);
    }
}
