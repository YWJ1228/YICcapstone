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

    // VoiceModel
    VOICE_MODEL_NOT_FOUND(400, "V001", "해당 VoiceModel을 찾을 수 없습니다."),

    // Member
    MEMBER_USERNAME_DUPLICATED(409, "M001", "이미 사용중인 이메일입니다."),
    MEMBER_NICKNAME_DUPLICATED(409, "M002", "이미 사용중인 닉네임입니다."),
    MEMBER_PASSWORD_INCORRETED(400, "M003", "비밀번호가 일치하지 않습니다."),
    MEMBER_NOT_EXIST(404, "M004", "존재하지 않는 회원입니다."),

    // Purchase
    VOICE_MODEL_PURCHASE_NOT_FOUND(400, "P001", "해당 음성모델을 구매하지 않았습니다."),
    EBOOK_PURCHASE_NOT_FOUND(400, "P002", "해당 Ebook을 구매하지 않았습니다."),
    VOICE_MODEL_PURCHASE_ALREADY_EXIST(400, "P003", "이미 구매한 음성모델입니다."),
    EBOOK_PURCHASE_ALREADY_EXIST(400, "P004", "이미 구매한 Ebook입니다."),

    // Review
    REVIEW_NOT_FOUND(400, "R001", "해당 리뷰를 찾을 수 없습니다."),
    REVIEW_ALREADY_WRITTEN(400, "R002", "이미 리뷰를 작성하였습니다."),
    REVIEW_NOT_WRITTEN(400, "R003", "리뷰를 작성하지 않았습니다."),
    REVIEW_NOT_WRITTEN_BY_MEMBER(400, "R004", "해당 리뷰를 작성한 회원이 아닙니다."),

    // Shopping Basket
    PRODUCT_ALREADY_PUT_INTO_BASKET(400, "S001", "장바구니에 이미 존재하는 상품입니다."),
    PRODUCT_NOT_EXIST_INTO_BASKET(400, "S002", "장바구니에 삭제할 대상이 존재하지 않습니다.");


    private final int status;
    private final String code;
    private final String message;
}
