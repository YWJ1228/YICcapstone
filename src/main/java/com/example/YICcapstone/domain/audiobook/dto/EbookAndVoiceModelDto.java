package com.example.YICcapstone.domain.audiobook.dto;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;

public record EbookAndVoiceModelDto(Long ebookId, Long voiceModelId) {
}
