package com.example.YICcapstone.domain.purchase.dto.response;

import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import com.example.YICcapstone.domain.purchase.domain.VoiceModelPurchase;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponse {
    private String userName;
    private String content;
    private int grade;
    private String time;

    public ReviewResponse(VoiceModelPurchase voiceModelPurchase) {
        this.userName = voiceModelPurchase.getMember().getName();
        this.content = voiceModelPurchase.getContent();
        this.grade = voiceModelPurchase.getGrade();
        this.time = timeFormat(voiceModelPurchase.getCreatedAt(), voiceModelPurchase.getUpdatedAt());
    }

    public ReviewResponse(EbookPurchase ebookPurchase) {
        this.userName = ebookPurchase.getMember().getName();
        this.content = ebookPurchase.getContent();
        this.grade = ebookPurchase.getGrade();
        this.time = timeFormat(ebookPurchase.getCreatedAt(), ebookPurchase.getUpdatedAt());
    }

    public String timeFormat(LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (updatedAt == null) {
            String year = createdAt.toString().substring(0, 4);
            String month = createdAt.toString().substring(5, 7);
            String day = createdAt.toString().substring(8, 10);
            return year + "-" + month + "-" + day;
        }
        else {
            String year = updatedAt.toString().substring(0, 4);
            String month = updatedAt.toString().substring(5, 7);
            String day = updatedAt.toString().substring(8, 10);
            return year + "-" + month + "-" + day + " (수정됨)";
        }
    }
}
