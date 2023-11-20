package com.example.YICcapstone.domain.audiobook.dto;

import com.example.YICcapstone.domain.audiobook.entity.AudioBook;
import lombok.Builder;
import lombok.Data;

@Data
public class AudioBookLinkDto {
    private String audioBookLink;

    @Builder
    public AudioBookLinkDto(AudioBook audioBook) {
        this.audioBookLink = audioBook.getAudioBookLink();
    }
}
