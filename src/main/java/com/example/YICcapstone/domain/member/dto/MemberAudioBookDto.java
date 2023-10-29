package com.example.YICcapstone.domain.member.dto;

import com.example.YICcapstone.domain.audiobook.entity.AudioBook;
import com.example.YICcapstone.domain.audiobookdownload.entity.AudioBookDownload;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.entity.Sex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MemberAudioBookDto (Member memberId, AudioBook audioBookId) { // 음성모델구매기록, EBook구매기록에 대한 외래키도 나중에 추가 필요
    public AudioBookDownload toEntity() {
        return AudioBookDownload.builder().
                memberId(memberId).audioBookId(audioBookId).build();
    }
}
