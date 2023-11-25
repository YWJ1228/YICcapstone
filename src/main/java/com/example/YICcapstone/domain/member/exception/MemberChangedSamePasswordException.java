package com.example.YICcapstone.domain.member.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class MemberChangedSamePasswordException extends BusinessException {
    public MemberChangedSamePasswordException()  { super(ErrorCode.MEMBER_CHANGED_SAME_PASSWORD); }
}
