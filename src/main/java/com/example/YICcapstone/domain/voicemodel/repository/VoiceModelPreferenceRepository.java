package com.example.YICcapstone.domain.voicemodel.repository;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModelPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoiceModelPreferenceRepository extends JpaRepository<VoiceModelPreference, Long> {
    Optional<VoiceModelPreference> findByMemberIdAndVoiceModelId(Long memberId, Long voiceModelId);
    Boolean existsByMemberIdAndVoiceModelId(Long memberId, Long voiceModelId);
}
