package com.example.YICcapstone.domain.voicemodel.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class VoiceModelNotFoundException extends BusinessException {
    public VoiceModelNotFoundException() {
        super(ErrorCode.VOICE_MODEL_NOT_FOUND);
    }
}
