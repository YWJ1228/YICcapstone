package com.example.YICcapstone.domain.ebook.exception;

import com.example.YICcapstone.global.error.exception.EntityNotFoundException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class EbookCategoryNotFoundException extends EntityNotFoundException {
    public EbookCategoryNotFoundException() {
        super(ErrorCode.EBOOK_CATEGORY_NOT_FOUND);
    }
}
