package com.example.YICcapstone.domain.review.domain;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.purchase.domain.VoiceModelPurchase;
import com.example.YICcapstone.domain.review.dto.request.ReviewCreationRequest;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VoiceModelReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voice_model_purchase_id")
    private VoiceModelPurchase voiceModelPurchase;

    @ManyToOne
    @JoinColumn(name = "voice_model_id")
    private VoiceModel voiceModel;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private String content;
    private int grade;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = null;
    private Boolean isDeleted = false;

    public VoiceModelReview(VoiceModelPurchase voiceModelPurchase, VoiceModel voiceModel, Member member, ReviewCreationRequest reviewCreationRequest) {
        this.voiceModelPurchase = voiceModelPurchase;
        this.voiceModel = voiceModel;
        this.member = member;
        this.content = reviewCreationRequest.getContent();
        this.grade = reviewCreationRequest.getGrade();
    }

}
