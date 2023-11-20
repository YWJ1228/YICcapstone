package com.example.YICcapstone.domain.feedback.dto;

import com.example.YICcapstone.domain.feedback.entity.Feedback;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FeedbackDto(
                @NotBlank(message = "제목을 입력해주세요.")
                @Size(max = 20, message = "제목은 최대 20자까지 입력이 가능합니다.")
                String title,

                @NotBlank(message = "내용을 입력해주세요.")
                @Size(max = 500, message = "내용은 최대 500자까지 입력이 가능합니다.")
                String detail) {
    public Feedback toEntity() {
        return Feedback.builder().
                title(title).detail(detail).build();
    }
}
