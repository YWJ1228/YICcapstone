package com.example.YICcapstone.domain.member.dto;

import com.example.YICcapstone.domain.audiobook.entity.AudioBook;
import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;

public record MemberAudioBookDto (Ebook ebook, VoiceModel voiceModel) { // 유저가 열람가능한 오디오북을 내 서재에서 이용하기 위해 임시로 만들어 둔 dto
    public AudioBook toEntity() {
        return AudioBook.builder()
                .ebook(ebook).voiceModel(voiceModel).build();
    }
}
