package com.example.YICcapstone.domain.review.repository;

import com.example.YICcapstone.domain.review.domain.VoiceModelReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoiceModelReviewRepository extends JpaRepository<VoiceModelReview, Long> {
    Optional<VoiceModelReview> findByVoiceModelIdAndMemberId(Long voiceModelId, Long MemberId);
}
