package com.example.YICcapstone.domain.purchase.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class VoiceModelPurchaseNotFoundException extends BusinessException {
    public VoiceModelPurchaseNotFoundException() {
        super(ErrorCode.VOICE_MODEL_PURCHASE_NOT_FOUND);
    }
}
