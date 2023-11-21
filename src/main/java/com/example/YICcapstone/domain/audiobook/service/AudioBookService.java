package com.example.YICcapstone.domain.audiobook.service;

import com.example.YICcapstone.domain.audiobook.dto.AudioBookLinkDto;
import com.example.YICcapstone.domain.audiobook.dto.EbookAndVoiceModelDto;

public interface AudioBookService {
    AudioBookLinkDto findAudioBook(EbookAndVoiceModelDto ebookAndVoiceModelDto);
    // E-Book과 음성모델에 매핑되는 오디오북 재생을 위한 URL 제공하기 (ebookId, voiceModelId)
}
