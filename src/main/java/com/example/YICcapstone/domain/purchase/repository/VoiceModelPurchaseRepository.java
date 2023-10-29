package com.example.YICcapstone.domain.purchase.repository;

import com.example.YICcapstone.domain.purchase.domain.VoiceModelPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoiceModelPurchaseRepository extends JpaRepository<VoiceModelPurchase, Long> {
}
