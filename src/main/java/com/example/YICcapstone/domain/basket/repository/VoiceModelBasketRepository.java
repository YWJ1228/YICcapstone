package com.example.YICcapstone.domain.basket.repository;

import com.example.YICcapstone.domain.basket.entity.VoiceModelBasket;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VoiceModelBasketRepository extends JpaRepository<VoiceModelBasket, Long> {
    @Query(value = "SELECT * FROM VOICE_MODEL_BASKET ORDER BY CREATED_AT DESC", nativeQuery = true)
    List<VoiceModelBasket> findByMember(Member member);
    VoiceModelBasket findByMemberAndVoiceModel(Member member, VoiceModel voiceModel);
}
