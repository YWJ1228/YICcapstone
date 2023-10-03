package com.example.YICcapstone.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewCreationRequest {
    @NotBlank(message = "리뷰 내용을 입력해주세요.")
    @Size(min = 2)
    private String content;
    @NotBlank(message = "리뷰 점수를 입력해주세요.")
    private int grade;
}
