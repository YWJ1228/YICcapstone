package com.example.YICcapstone.domain.audiobookdownload.entity;

import com.example.YICcapstone.domain.audiobook.entity.AudioBook;
import com.example.YICcapstone.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class AudioBookDownload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne // AudioBookDownload:many, Member:one (외래키)
    @JoinColumn
    private Member memberId;

    @ManyToOne // AudioBookDownload:many, AudioBook:one (외래키)
    @JoinColumn
    private AudioBook audioBookId;

    /*
    @ManyToOne 음성모델구매기록, EBook구매기록도 추후 추가 필요
     */

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime downloadedAt;

    public void setMemberId (Member memberId) {
        this.memberId = memberId;
    }
    public void setAudioBookId (AudioBook audioBookId) {
        this.audioBookId = audioBookId;
    }
    // 해당 오디오북 다운로드 기록이 어떤 AudioBook을 어떤 Member가 관련된 것인지 외래키 설정 (setEbookId, setVoiceModelId)

    /*
    음성모델구매기록, EBook구매기록도 추후 외래키 설정 메서드 추가 필요
    */
}
