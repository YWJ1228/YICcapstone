package com.example.YICcapstone.domain.purchase.domain;


import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.purchase.dto.request.ReviewRequest;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Column(name = "order_id")
    private Long orderId;
    private LocalDateTime purchaseAt = LocalDateTime.now();

    @Size(max = 300)
    private String content = null;
    private int grade;
    private LocalDateTime createdAt = null;
    private LocalDateTime updatedAt = null;
    private Boolean isDeleted = false;

    public VoiceModelPurchase(VoiceModel voiceModel, Member member, Long orderId) {
        this.voiceModel = voiceModel;
        this.member = member;
        this.orderId = orderId;
    }

    public void createReview(ReviewRequest reviewRequest) {
        this.content = reviewRequest.getContent();
        this.grade = reviewRequest.getGrade();
        this.createdAt = LocalDateTime.now();
    }

    public void updateReview(ReviewRequest reviewRequest) {
        this.content = reviewRequest.getContent();
        this.grade = reviewRequest.getGrade();
        this.updatedAt = LocalDateTime.now();
    }

    public void deleteReview() {
        this.isDeleted = true;
    }
}
