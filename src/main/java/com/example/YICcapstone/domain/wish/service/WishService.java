package com.example.YICcapstone.domain.wish.service;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.exception.EbookNotFoundException;
import com.example.YICcapstone.domain.ebook.repository.EbookRepository;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.exception.MemberNotExistException;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.exception.VoiceModelNotFoundException;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelRepository;
import com.example.YICcapstone.domain.wish.domain.EbookWish;
import com.example.YICcapstone.domain.wish.domain.VoiceModelWish;
import com.example.YICcapstone.domain.wish.dto.response.EbookWishResponse;
import com.example.YICcapstone.domain.wish.dto.response.VoiceModelWishResponse;
import com.example.YICcapstone.domain.wish.repository.EbookWishRepository;
import com.example.YICcapstone.domain.wish.repository.VoiceModelWishRepository;
import com.example.YICcapstone.global.util.security.SecurityUtil;
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
        Member member = verifyMember();
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
        Member member = verifyMember();
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
    public Page<EbookWishResponse> getEbookWishList(int page, int size) {
        Member member = verifyMember();
        Page<EbookWish> savedEbookWishList = ebookWishRepository.findAllByMemberIdOrderByCreatedAtDesc(member, PageRequest.of(page, size));
        return savedEbookWishList.map(
                ebookWish -> new EbookWishResponse(ebookWish.getId(), ebookWish.getEbook())
        );
    }

    @Transactional(readOnly = true)
    public Page<VoiceModelWishResponse> getVoiceModelWishList(int page, int size) {
        Member member = verifyMember();
        Page<VoiceModelWish> savedVoiceModelList = voiceModelWishRepository.findAllByMemberIdOrderByCreatedAtDesc(member, PageRequest.of(page, size));
        return savedVoiceModelList.map(
                voiceModelWish -> new VoiceModelWishResponse(voiceModelWish.getId(), voiceModelWish.getVoiceModel())
        );
    }

    public Boolean ebookWishVerify(Long ebookId) {
        Member member = verifyMember();
        return ebookWishRepository.existsByMemberIdAndEbookId(member, ebookId);
    }

    public Boolean voiceModelWishVerify(Long voiceModelId) {
        Member member = verifyMember();
        return voiceModelWishRepository.existsByMemberIdAndVoiceModelId(member, voiceModelId);
    }

    public Member verifyMember() {
        return memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());
    }
}
