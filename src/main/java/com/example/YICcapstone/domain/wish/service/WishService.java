package com.example.YICcapstone.domain.wish.service;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.exception.EbookNotFoundException;
import com.example.YICcapstone.domain.ebook.repository.EbookRepository;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.exception.VoiceModelNotFoundException;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelRepository;
import com.example.YICcapstone.domain.wish.domain.EbookWish;
import com.example.YICcapstone.domain.wish.domain.VoiceModelWish;
import com.example.YICcapstone.domain.wish.repository.EbookWishRepository;
import com.example.YICcapstone.domain.wish.repository.VoiceModelWishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WishService {
    @Autowired
    private EbookWishRepository ebookWishRepository;
    @Autowired
    private VoiceModelWishRepository voiceModelWishRepository;
    @Autowired
    private EbookRepository ebookRepository;
    @Autowired
    private VoiceModelRepository voiceModelRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public void wishEbook(Long ebookId) {
        //TODO: 유저 검증
        Member member = memberRepository.findById(1L) //임의값 설정 나중에 변경 필요
                .orElseThrow();
        Ebook savedEbook = ebookRepository.findById(ebookId)
                .orElseThrow(EbookNotFoundException::new);
        EbookWish savedEbookWish = ebookWishRepository.findByMemberIdAndEbookId(member, savedEbook)
                .orElse(null);
        if (savedEbookWish == null) {
            EbookWish createEbookWish = new EbookWish(member, savedEbook);
            ebookWishRepository.save(createEbookWish);
        } else {
            ebookWishRepository.delete(savedEbookWish);
        }
    }

    @Transactional
    public void wishVoiceModel(Long voiceModelId) {
        //TODO: 유저 검증
        Member member = memberRepository.findById(1L) //임의값 설정 나중에 변경 필요
                .orElseThrow();
        VoiceModel savedVoiceModel = voiceModelRepository.findById(voiceModelId)
                .orElseThrow(VoiceModelNotFoundException::new);
        VoiceModelWish savedVoiceModelWish = voiceModelWishRepository.findByMemberIdAndVoiceModelId(member, savedVoiceModel)
                .orElse(null);
        if (savedVoiceModelWish == null) {
            VoiceModelWish createVoiceModelWish = new VoiceModelWish(member, savedVoiceModel);
            voiceModelWishRepository.save(createVoiceModelWish);
        } else {
            voiceModelWishRepository.delete(savedVoiceModelWish);
        }
    }

    @Transactional(readOnly = true)
    public Page<EbookWish> getEbookWishList(int page) {
        Member member = memberRepository.findById(1L) //임의값 설정 나중에 변경 필요
                .orElseThrow();
        return ebookWishRepository.findAllByMemberIdOrderByCreatedAtDesc(member, PageRequest.of(page, 10));
    }

    @Transactional(readOnly = true)
    public Page<VoiceModelWish> getVoiceModelWishList(int page) {
        Member member = memberRepository.findById(1L) //임의값 설정 나중에 변경 필요
                .orElseThrow();
        return voiceModelWishRepository.findAllByMemberIdOrderByCreatedAtDesc(member, PageRequest.of(page, 10));
    }

    public Boolean ebookWishVerify(Long ebookId) {
        Member member = memberRepository.findById(1L) //임의값 설정 나중에 변경 필요
                .orElseThrow();
        return ebookWishRepository.existsByMemberIdAndEbookId(member, ebookId);
    }

    public Boolean voiceModelWishVerify(Long voiceModelId) {
        Member member = memberRepository.findById(1L) //임의값 설정 나중에 변경 필요
                .orElseThrow();
        return voiceModelWishRepository.existsByMemberIdAndVoiceModelId(member, voiceModelId);
    }
}
