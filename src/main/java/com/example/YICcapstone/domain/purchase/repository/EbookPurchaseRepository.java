package com.example.YICcapstone.domain.purchase.repository;

import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EbookPurchaseRepository extends JpaRepository<EbookPurchase, Long> {
    Optional<EbookPurchase> findByEbookIdAndMemberId(Long ebookId, Long memberId);

    // 구매 리스트 조회
    Page<EbookPurchase> findAllByMemberIdOrderByPurchasedAtDesc(Long memberId, Pageable pageable);

    // 리뷰 작성하지 않은 구매 리스트 조회
    Page<EbookPurchase> findAllByMemberIdAndContentIsNullOrderByPurchasedAtDesc(Long memberId, Pageable pageable);

    // 리뷰 작성한 구매 리스트 조회
    Page<EbookPurchase> findAllByMemberIdAndContentIsNotNullOrderByPurchasedAtDesc(Long memberId, Pageable pageable);

    // 리뷰 리스트 조회
    Page<EbookPurchase> findAllByEbookIdAndContentIsNotNullOrderByCreatedAtDesc(Long ebookId, Pageable pageable);
}
