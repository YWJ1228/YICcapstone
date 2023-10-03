package com.example.YICcapstone.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    // Common
    INTERNAL_SERVER_ERROR(500, "C001", "서버에 문제가 발생하였습니다."),
    INVALID_TYPE_VALUE(400, "C002", "요청 타입이 잘못되었습니다."),
    INVALID_INPUT_VALUE(400, "C003", "입력값이 잘못되었습니다."),
    METHOD_NOT_ALLOWED(405, "C004", "요청된 메소드가 잘못되었습니다."),
    HANDLE_ACCESS_DENIED(403, "C005", "권한이 없습니다."),
    ENTITY_NOT_FOUND(400, "C006", "해당 엔티티를 찾을 수 없습니다."),

    // Ebook
    EBOOK_NOT_FOUND(400, "E001", "해당 Ebook을 찾을 수 없습니다."),
    EBOOK_CATEGORY_NOT_FOUND(400, "E002", "해당 EbookCategory를 찾을 수 없습니다."),

    // VoiceModel
    VOICE_MODEL_NOT_FOUND(400, "V001", "해당 VoiceModel을 찾을 수 없습니다."),
    VOICE_MODEL_CATEGORY_NOT_FOUND(400, "V002", "해당 VoiceModelCategory를 찾을 수 없습니다."),

    // Purchase
    PURCHASE_NOT_FOUND(400, "P001", "유효한 구매내역이 없습니다."),
    PURCHASE_ALREADY_EXIST(400, "P002", "이미 구매한 상품입니다."),
    PURCHASE_NOT_EXIST(400, "P003", "구매하지 않은 상품입니다."),
    PURCHASE_NOT_MATCH(400, "P004", "구매한 상품과 일치하지 않습니다."),

    // Review
    REVIEW_NOT_FOUND(400, "R001", "해당 Review를 찾을 수 없습니다."),
    REVIEW_ALREADY_EXIST(400, "R002", "이미 리뷰를 작성한 상품입니다.");

    private final int status;
    private final String code;
    private final String message;
}
