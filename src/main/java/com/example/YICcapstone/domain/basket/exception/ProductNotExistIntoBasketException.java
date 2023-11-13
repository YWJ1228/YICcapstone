package com.example.YICcapstone.domain.basket.exception;

import com.example.YICcapstone.global.error.exception.EntityNotFoundException;
import com.example.YICcapstone.global.error.exception.ErrorCode;

public class ProductNotExistIntoBasketException extends EntityNotFoundException {
    public ProductNotExistIntoBasketException() {
        super(ErrorCode.PRODUCT_NOT_EXIST_INTO_BASKET);
    }
}
