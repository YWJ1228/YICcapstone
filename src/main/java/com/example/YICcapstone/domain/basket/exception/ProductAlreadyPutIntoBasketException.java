package com.example.YICcapstone.domain.basket.exception;

import com.example.YICcapstone.global.error.exception.BusinessException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class ProductAlreadyPutIntoBasketException extends BusinessException {
    public ProductAlreadyPutIntoBasketException() {super(ErrorCode.PRODUCT_ALREADY_PUT_INTO_BASKET);}
}
