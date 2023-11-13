package com.example.YICcapstone.domain.basket.dto;

import com.example.YICcapstone.domain.basket.entity.VoiceModelBasket;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoiceModelBasketDto {
    private Long id;
    private String category;
    private String celebrityName;
    private int price;
    private String imageUrl;
    //private VoiceModel voiceModel;
    //private Member member;

    @Builder
    public VoiceModelBasketDto(VoiceModelBasket voiceModelBasket) {
        this.id = voiceModelBasket.getVoiceModel().getId();
        this.category = voiceModelBasket.getVoiceModel().getCategory();
        this.celebrityName = voiceModelBasket.getVoiceModel().getCelebrityName();
        this.price = voiceModelBasket.getVoiceModel().getPrice();
        this.imageUrl = voiceModelBasket.getVoiceModel().getImageUrl();
        //this.voiceModel = voiceModelBasket.getVoiceModel();
        //this.member = voiceModelBasket.getMember();
    }
}
