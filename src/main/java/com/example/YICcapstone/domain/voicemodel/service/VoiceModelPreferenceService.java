package com.example.YICcapstone.domain.voicemodel.service;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.exception.MemberNotExistException;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModelPreference;
import com.example.YICcapstone.domain.voicemodel.exception.VoiceModelNotFoundException;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelPreferenceRepository;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelRepository;
import com.example.YICcapstone.global.util.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoiceModelPreferenceService {
    @Autowired
    private VoiceModelPreferenceRepository preferenceRepository;
    @Autowired
    private VoiceModelRepository voiceModelRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public Integer preferVoiceModel(Long voiceModelId) {
        Member member = verifyMember();
        VoiceModel savedVoiceModel = voiceModelRepository.findById(voiceModelId)
                .orElseThrow(VoiceModelNotFoundException::new);
        VoiceModelPreference preference = preferenceRepository.findByMemberIdAndVoiceModelId(member, savedVoiceModel)
                .orElse(null);

        if (preference == null) {
            preference = new VoiceModelPreference(member, savedVoiceModel);
            preferenceRepository.save(preference);
        } else {
            preferenceRepository.delete(preference);
        }
        return getPreferenceCount(voiceModelId);
    }

    public Integer getPreferenceCount(Long voiceModelId) {
        VoiceModel voiceModel = voiceModelRepository.findById(voiceModelId)
                .orElseThrow(VoiceModelNotFoundException::new);
        return preferenceRepository.countByVoiceModelId(voiceModelId);
    }

    public Boolean preferenceVerify(Long voiceModelId) {
        Member member = verifyMember();
        return preferenceRepository.existsByMemberIdAndVoiceModelId(member, voiceModelId);
    }

    public Member verifyMember() {
        return memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());
    }
}
