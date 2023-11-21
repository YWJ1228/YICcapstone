package com.example.YICcapstone.domain.audiobook.exception;

import com.example.YICcapstone.global.error.exception.EntityNotFoundException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class AudioBookNotFoundException extends EntityNotFoundException {
    public AudioBookNotFoundException() {super(ErrorCode.AUDIO_BOOK_NOT_FOUND);}
}
