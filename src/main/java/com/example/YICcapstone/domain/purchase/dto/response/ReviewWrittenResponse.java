package com.example.YICcapstone.domain.purchase.dto.response;

import com.example.YICcapstone.domain.ebook.dto.response.EbookResponse;
import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import com.example.YICcapstone.domain.purchase.domain.VoiceModelPurchase;
import com.example.YICcapstone.domain.voicemodel.dto.response.VoiceModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class ReviewWrittenResponse {
    private Long purchaseId;
    private String purchasedAt;
    private Optional<EbookResponse> ebook;
    private Optional<VoiceModelResponse> voiceModel;
    private String content;
    private Integer grade;
    private String createdAt;

    public ReviewWrittenResponse(VoiceModelPurchase voiceModelPurchase) {
        this.purchaseId = voiceModelPurchase.getId();
        this.purchasedAt = timeFormat(voiceModelPurchase.getPurchasedAt());
        this.content = voiceModelPurchase.getContent();
        this.createdAt = timeFormat(voiceModelPurchase.getCreatedAt(), voiceModelPurchase.getUpdatedAt());
        this.ebook = Optional.empty();
        this.grade = null;
        this.voiceModel = Optional.of(new VoiceModelResponse(voiceModelPurchase.getVoiceModel()));
    }

    public ReviewWrittenResponse(EbookPurchase ebookPurchase) {
        this.purchaseId = ebookPurchase.getId();
        this.purchasedAt = timeFormat(ebookPurchase.getPurchasedAt());
        this.content = ebookPurchase.getContent();
        this.grade = ebookPurchase.getGrade();
        this.createdAt = timeFormat(ebookPurchase.getCreatedAt(), ebookPurchase.getUpdatedAt());
        this.ebook = Optional.of(new EbookResponse(ebookPurchase.getEbook()));
        this.voiceModel = Optional.empty();
    }

    public String timeFormat(LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (createdAt == null && updatedAt == null)
            return null;
        else if (updatedAt == null) {
            String year = createdAt.toString().substring(0, 4);
            String month = createdAt.toString().substring(5, 7);
            String day = createdAt.toString().substring(8, 10);
            return year + "-" + month + "-" + day;
        } else {
            String year = updatedAt.toString().substring(0, 4);
            String month = updatedAt.toString().substring(5, 7);
            String day = updatedAt.toString().substring(8, 10);
            return year + "-" + month + "-" + day + " (수정됨)";
        }
    }

    public String timeFormat(LocalDateTime purchasedAt) {
        String year = purchasedAt.toString().substring(0, 4);
        String month = purchasedAt.toString().substring(5, 7);
        String day = purchasedAt.toString().substring(8, 10);
        return year + "-" + month + "-" + day;
    }
}
