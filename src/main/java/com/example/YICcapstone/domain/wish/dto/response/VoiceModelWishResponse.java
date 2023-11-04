package com.example.YICcapstone.domain.wish.dto.response;

import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.dto.response.VoiceModelResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class VoiceModelWishResponse {
    private Long wishId;
    private VoiceModelResponse voiceModel;

    public VoiceModelWishResponse(Long wishId, VoiceModel voiceModel){
        this.wishId = wishId;
        this.voiceModel = new VoiceModelResponse(voiceModel);
    }
}
