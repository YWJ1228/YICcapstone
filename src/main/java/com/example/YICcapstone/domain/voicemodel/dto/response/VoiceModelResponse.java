package com.example.YICcapstone.domain.voicemodel.dto.response;


import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.exception.VoiceModelNotFoundException;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelPreferenceRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Setter
public class VoiceModelResponse {

    private Long id;
    private String category;
    private String voiceModelUrl;
    private String celebrityName;
    private int price;
    private String imageUrl;
    private String comment;
    private String sampleUrl;
    private int viewCount;
    private Integer purchaseCount;
    private Integer preferenceCount;
    private String uploadedAt;

    public VoiceModelResponse(VoiceModel voiceModel) {
        id = voiceModel.getId();
        category = voiceModel.getCategory();
        voiceModelUrl = voiceModel.getVoiceModelUrl();
        celebrityName = voiceModel.getCelebrityName();
        price = voiceModel.getPrice();
        imageUrl = voiceModel.getImageUrl();
        comment = voiceModel.getComment();
        sampleUrl = voiceModel.getSampleUrl();
        viewCount = voiceModel.getViewCount();
        purchaseCount = voiceModel.getVoiceModelPurchaseList().size();
        preferenceCount = voiceModel.getVoiceModelPreferenceList().size();
        uploadedAt = timeFormat(voiceModel.getUploadedAt());
    }

    public String timeFormat(LocalDateTime time) {
        String year = time.toString().substring(0,4);
        String month = time.toString().substring(5,7);
        String day = time.toString().substring(8,10);
        return year + "년 " + month + "월 " + day + "일";
    }
}
