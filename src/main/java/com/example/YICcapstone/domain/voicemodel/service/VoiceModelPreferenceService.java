package com.example.YICcapstone.domain.voicemodel.service;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModelPreference;
import com.example.YICcapstone.domain.voicemodel.exception.VoiceModelNotFoundException;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelPreferenceRepository;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelRepository;
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
        //TODO: 유저 검증
        Member member = memberRepository.findById(1L) //임의값 설정 나중에 변경 필요
                .orElseThrow();
        VoiceModel savedVoiceModel = voiceModelRepository.findById(voiceModelId)
                .orElseThrow(VoiceModelNotFoundException::new);
        VoiceModelPreference preference = preferenceRepository.findByMemberIdAndVoiceModelId(member, savedVoiceModel)
                .orElse(null);

        if (preference == null) {
            preference = new VoiceModelPreference(member, savedVoiceModel);
            savedVoiceModel.setPreferenceCount(savedVoiceModel.getPreferenceCount() + 1);
            preferenceRepository.save(preference);
        } else {
            savedVoiceModel.setPreferenceCount(savedVoiceModel.getPreferenceCount() - 1);
            preferenceRepository.delete(preference);
        }
        return savedVoiceModel.getPreferenceCount();
    }

    public Boolean preferenceVerify(Long voiceModelId){
        Member member = memberRepository.findById(1L) //임의값 설정 나중에 변경 필요
                .orElseThrow();
        return preferenceRepository.existsByMemberIdAndVoiceModelId(member, voiceModelId);
    }
}
