package com.example.YICcapstone.domain.purchase.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class EbookPurchaseAlreadyExistException extends BusinessException {
    public EbookPurchaseAlreadyExistException() {
        super(ErrorCode.EBOOK_PURCHASE_ALREADY_EXIST);
    }
}
