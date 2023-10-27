package com.example.YICcapstone.domain.audiobook.entity;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class AudioBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne // AudioBook:many, Ebook:one (외래키)
    @JoinColumn
    private Ebook ebookId;

    @ManyToOne // AudioBook:many, VoiceModel:one (외래키)
    @JoinColumn
    private VoiceModel voiceModelId;

    // 외래키의 관리는 Many 측에서 관리하는 것이 좋다 -> insert 또는 update 시, 쿼리문 개수를 줄일 수 있음
    // 단방향 참조는 Many 측에서 @ManyToOne을 사용해주면 끝, 양방향 참조 목적은 one 측에서도 @OneToMany를 선언해주어야 함
    // Many(A) to Many(B)는 권장 X -> 각 Many의 키들을 가져와 One(A) to Many(C), Many(C) to One(B)으로 중간단계 C 테이블을 추가 설계가 필요함

    @Column(length = 255, nullable = false, unique = true)
    private String audioBookLink;

    public void setEbookId (Ebook ebookId) {
        this.ebookId = ebookId;
    }
    public void setVoiceModelId (VoiceModel voiceModelId) {
        this.voiceModelId = voiceModelId;
    }
    // 해당 오디오북이 어떤 Ebook과 어떤 VoiceModel의 합성 결과물인지 포함하기 위한 외래키 설정 (setEbookId, setVoiceModelId)
}
