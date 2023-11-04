package com.example.YICcapstone.domain.wish.repository;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.wish.domain.VoiceModelWish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoiceModelWishRepository extends JpaRepository<VoiceModelWish, Long> {
    Optional<VoiceModelWish> findByMemberIdAndVoiceModelId(Long memberId, Long voiceModelId);
    Page<VoiceModelWish> findAllByMemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);
    Boolean existsByMemberIdAndVoiceModelId(Long memberId, Long voiceModelId);
}
