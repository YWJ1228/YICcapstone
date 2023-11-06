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

    // 구매 리스트 조회
    Page<VoiceModelPurchase> findAllByMemberIdOrderByPurchasedAtDesc(Long memberId, Pageable pageable);

    // 리뷰 리스트 조회
    Page<VoiceModelPurchase> findAllByVoiceModelIdAndContentIsNotNullAndIsDeletedIsFalseOrderByCreatedAtDesc(Long voiceModelId, Pageable pageable);
}
