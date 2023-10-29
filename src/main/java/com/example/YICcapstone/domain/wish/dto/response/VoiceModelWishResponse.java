package com.example.YICcapstone.domain.wish.dto.response;

import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VoiceModelWishResponse {
    Long id;
    VoiceModel voiceModel;
}
