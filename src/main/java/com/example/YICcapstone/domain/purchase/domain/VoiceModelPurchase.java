package com.example.YICcapstone.domain.purchase.domain;


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
public class VoiceModelPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voice_model_id")
    private VoiceModel voiceModel;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private LocalDateTime purchaseAt = LocalDateTime.now();

    public VoiceModelPurchase(VoiceModel voiceModel, Member member) {
        this.voiceModel = voiceModel;
        this.member = member;
    }
}
