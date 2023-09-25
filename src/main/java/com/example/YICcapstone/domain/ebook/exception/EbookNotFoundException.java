package com.example.YICcapstone.domain.ebook.exception;

import com.example.YICcapstone.global.error.exception.EntityNotFoundException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class EbookNotFoundException extends EntityNotFoundException {
    public EbookNotFoundException() {
        super(ErrorCode.EBOOK_NOT_FOUND);
    }
}
