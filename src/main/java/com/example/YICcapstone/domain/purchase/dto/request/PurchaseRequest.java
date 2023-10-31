package com.example.YICcapstone.domain.purchase.dto.request;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import com.example.YICcapstone.domain.purchase.domain.VoiceModelPurchase;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import lombok.Getter;

@Getter
public class PurchaseRequest {
    private Long itemId;
    private Long orderId;

    public VoiceModelPurchase toVoiceModelPurchase(VoiceModel voiceModel, Member member) {
        return new VoiceModelPurchase(voiceModel, member, orderId);
    }

    public EbookPurchase toEbookPurchase(Ebook savedEbook, Member member) {
        return new EbookPurchase(savedEbook, member, orderId);
    }
}
