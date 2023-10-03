package com.example.YICcapstone.domain.review.repository;

import com.example.YICcapstone.domain.review.domain.EbookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EbookReviewRepository extends JpaRepository<EbookReview, Long> {
    Optional<EbookReview> findByEbookIdAndMemberId(Long ebookId, Long MemberId);
}
