package com.example.YICcapstone.domain.purchase.repository;

import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EbookPurchaseRepository extends JpaRepository<EbookPurchase, Long> {
}
