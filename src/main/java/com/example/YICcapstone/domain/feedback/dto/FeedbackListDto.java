package com.example.YICcapstone.domain.feedback.dto;

import com.example.YICcapstone.domain.feedback.entity.Feedback;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class FeedbackListDto {
    private Long id;
    private String username;
    private String title;
    private String detail;
    private String createdAt;

    @Builder
    public FeedbackListDto(Feedback feedback) {
        this.id = feedback.getId();
        this.username = feedback.getUsername();
        this.title = feedback.getTitle();
        this.detail = feedback.getDetail();
        this.createdAt =  feedback.getCreatedAt().format(DateTimeFormatter.ISO_DATE);
    }
}
