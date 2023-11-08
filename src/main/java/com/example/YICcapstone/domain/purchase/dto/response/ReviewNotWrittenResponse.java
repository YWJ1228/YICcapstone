package com.example.YICcapstone.domain.purchase.dto.response;

import com.example.YICcapstone.domain.ebook.dto.response.EbookResponse;
import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import com.example.YICcapstone.domain.purchase.domain.VoiceModelPurchase;
import com.example.YICcapstone.domain.voicemodel.dto.response.VoiceModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class ReviewNotWrittenResponse {
    private Long purchaseId;
    private String purchasedAt;
    private Optional<EbookResponse> ebook;
    private Optional<VoiceModelResponse> voiceModel;

    public ReviewNotWrittenResponse(VoiceModelPurchase voiceModelPurchase) {
        this.purchaseId = voiceModelPurchase.getId();
        this.purchasedAt = timeFormat(voiceModelPurchase.getPurchasedAt());
        this.ebook = Optional.empty();
        this.voiceModel = Optional.of(new VoiceModelResponse(voiceModelPurchase.getVoiceModel()));
    }

    public ReviewNotWrittenResponse(EbookPurchase ebookPurchase) {
        this.purchaseId = ebookPurchase.getId();
        this.purchasedAt = timeFormat(ebookPurchase.getPurchasedAt());
        this.ebook = Optional.of(new EbookResponse(ebookPurchase.getEbook()));
        this.voiceModel = Optional.empty();
    }

    public String timeFormat(LocalDateTime purchasedAt) {
        String year = purchasedAt.toString().substring(0, 4);
        String month = purchasedAt.toString().substring(5, 7);
        String day = purchasedAt.toString().substring(8, 10);
        return year + "-" + month + "-" + day;
    }
}
