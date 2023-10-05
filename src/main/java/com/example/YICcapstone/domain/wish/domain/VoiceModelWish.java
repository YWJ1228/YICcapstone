package com.example.YICcapstone.domain.wish.domain;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VoiceModelWish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "voice_model_id")
    private VoiceModel voiceModel;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    public VoiceModelWish(Member member, VoiceModel voiceModel){
        this.member = member;
        this.voiceModel = voiceModel;
    }
}
