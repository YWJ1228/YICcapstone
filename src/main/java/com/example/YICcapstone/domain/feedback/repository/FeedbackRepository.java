package com.example.YICcapstone.domain.feedback.repository;

import com.example.YICcapstone.domain.feedback.entity.Feedback;
import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query(value = "SELECT * FROM FEEDBACK ORDER BY CREATED_AT DESC", nativeQuery = true)
    Page<Feedback> findAll(Pageable pageable);
}
