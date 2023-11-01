package com.example.YICcapstone.domain.purchase.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ReviewRequest {
    @NotNull(message = "구매 아이디를 입력해주세요.")
    private Long purchaseId;
    @NotBlank(message = "리뷰 내용을 입력해주세요.")
    @Size(max = 300)
    private String content;
    @NotNull(message = "리뷰 평점을 입력해주세요.")
    private int grade;
}
