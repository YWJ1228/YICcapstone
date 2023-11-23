package com.example.YICcapstone.domain.purchase.repository;

import com.example.YICcapstone.domain.purchase.domain.VoiceModelPurchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoiceModelPurchaseRepository extends JpaRepository<VoiceModelPurchase, Long> {
    Optional<VoiceModelPurchase> findByVoiceModelIdAndMemberId(Long voiceModelId, Long memberId);
    Optional<VoiceModelPurchase> findByIdAndMemberId(Long purchaseId, Long memberId);

    // 구매 리스트 조회
    Page<VoiceModelPurchase> findAllByMemberIdOrderByPurchasedAtDesc(Long memberId, Pageable pageable);

    // 리뷰 작성하지 않은 구매 리스트 조회
    Page<VoiceModelPurchase> findAllByMemberIdAndContentIsNullOrderByPurchasedAtDesc(Long memberId, Pageable pageable);

    // 리뷰 작성한 구매 리스트 조회
    Page<VoiceModelPurchase> findAllByMemberIdAndContentIsNotNullOrderByPurchasedAtDesc(Long memberId, Pageable pageable);

    // 리뷰 리스트 조회
    Page<VoiceModelPurchase> findAllByVoiceModelIdAndContentIsNotNullOrderByCreatedAtDesc(Long voiceModelId, Pageable pageable);
}
