package com.example.YICcapstone.domain.voicemodel.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class VoiceModelCategoryNotFoundException extends BusinessException {
    public VoiceModelCategoryNotFoundException() {
        super(ErrorCode.VOICE_MODEL_CATEGORY_NOT_FOUND);
    }
}
