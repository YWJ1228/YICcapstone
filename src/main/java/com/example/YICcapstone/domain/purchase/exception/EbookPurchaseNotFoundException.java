package com.example.YICcapstone.domain.purchase.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class EbookPurchaseNotFoundException extends BusinessException {
    public EbookPurchaseNotFoundException() {
        super(ErrorCode.EBOOK_PURCHASE_NOT_FOUND);
    }
}
