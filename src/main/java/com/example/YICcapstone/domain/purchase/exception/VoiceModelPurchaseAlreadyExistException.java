package com.example.YICcapstone.domain.purchase.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class VoiceModelPurchaseAlreadyExistException extends BusinessException {
    public VoiceModelPurchaseAlreadyExistException() {
        super(ErrorCode.VOICE_MODEL_PURCHASE_ALREADY_EXIST);
    }
}
