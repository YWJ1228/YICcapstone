package com.example.YICcapstone.domain.purchase.domain;


import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.purchase.dto.request.EbookReviewRequest;
import com.example.YICcapstone.domain.purchase.dto.request.VoiceModelReviewRequest;
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
    @Column(name = "payment_method")
    private String paymentMethod;
    private Integer price = null;
    private LocalDateTime purchasedAt = LocalDateTime.now();

    @Size(max = 300)
    private String content = null;
    private LocalDateTime createdAt = null;
    private LocalDateTime updatedAt = null;

    public VoiceModelPurchase(VoiceModel voiceModel, Member member, Long orderId, String paymentMethod, Integer price) {
        this.voiceModel = voiceModel;
        this.member = member;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.price = price;
    }

    public void createReview(VoiceModelReviewRequest voiceModelReviewRequest) {
        this.content = voiceModelReviewRequest.getContent();
        this.createdAt = LocalDateTime.now();
    }

    public void updateReview(VoiceModelReviewRequest voiceModelReviewRequest) {
        this.content = voiceModelReviewRequest.getContent();
        this.updatedAt = LocalDateTime.now();
    }

    public void deleteReview() {
        this.content = null;
        this.createdAt = null;
        this.updatedAt = null;
    }
}
