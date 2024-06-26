package com.example.YICcapstone.domain.audiobook.service;

import com.example.YICcapstone.domain.audiobook.dto.AudioBookLinkDto;

public interface AudioBookService {
    AudioBookLinkDto findAudioBook(Long ebookId, Long voiceModelId);
    // E-Book과 음성모델에 매핑되는 오디오북 재생을 위한 URL 제공하기 (ebookId, voiceModelId)
}
