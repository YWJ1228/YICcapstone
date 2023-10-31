package com.example.YICcapstone.domain.purchase.repository;

import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EbookPurchaseRepository extends JpaRepository<EbookPurchase, Long> {
    Optional<EbookPurchase> findByEbookIdAndMemberId(Long ebookId, Long memberId);
}
