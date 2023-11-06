package com.example.YICcapstone.domain.purchase.service;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.exception.EbookNotFoundException;
import com.example.YICcapstone.domain.ebook.repository.EbookRepository;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.exception.MemberNotExistException;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.purchase.domain.VoiceModelPurchase;
import com.example.YICcapstone.domain.purchase.dto.request.PurchaseRequest;
import com.example.YICcapstone.domain.purchase.dto.request.ReviewRequest;
import com.example.YICcapstone.domain.purchase.dto.response.PurchaseResponse;
import com.example.YICcapstone.domain.purchase.exception.VoiceModelPurchaseAlreadyExistException;
import com.example.YICcapstone.domain.purchase.exception.VoiceModelPurchaseNotFoundException;
import com.example.YICcapstone.domain.purchase.repository.EbookPurchaseRepository;
import com.example.YICcapstone.domain.purchase.repository.VoiceModelPurchaseRepository;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.exception.VoiceModelNotFoundException;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelRepository;
import com.example.YICcapstone.global.util.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseService {
    @Autowired
    private VoiceModelPurchaseRepository voiceModelPurchaseRepository;
    @Autowired
    private EbookPurchaseRepository ebookPurchaseRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private VoiceModelRepository voiceModelRepository;
    @Autowired
    private EbookRepository ebookRepository;

    @Transactional
    public void createVoiceModelPurchase(PurchaseRequest purchaseRequest) {
        Member member = verifyMember();
        VoiceModel savedVoiceModel = voiceModelRepository.findById(purchaseRequest.getItemId())
                .orElseThrow(VoiceModelNotFoundException::new);
        if (voiceModelPurchaseRepository.findByVoiceModelIdAndMemberId(savedVoiceModel.getId(), member.getId()).isPresent())
            throw new VoiceModelPurchaseAlreadyExistException();
        voiceModelPurchaseRepository.save(purchaseRequest.toVoiceModelPurchase(savedVoiceModel, member));
    }

    @Transactional
    public void createEbookPurchase(PurchaseRequest purchaseRequest) {
        Member member = verifyMember();
        Ebook savedEbook = ebookRepository.findById(purchaseRequest.getItemId())
                .orElseThrow(EbookNotFoundException::new);
        if (ebookPurchaseRepository.findByEbookIdAndMemberId(savedEbook.getId(), member.getId()).isPresent())
            throw new VoiceModelPurchaseAlreadyExistException();
        ebookPurchaseRepository.save(purchaseRequest.toEbookPurchase(savedEbook, member));
    }

    @Transactional(readOnly = true)
    public Page<PurchaseResponse> getVoiceModelPurchaseList(int page, int size) {
        Member member = verifyMember();
        return voiceModelPurchaseRepository.findAllByMemberIdOrderByPurchasedAtDesc(member.getId(), PageRequest.of(page, size))
                .map(PurchaseResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<PurchaseResponse> getEbookPurchaseList(int page, int size) {
        Member member = verifyMember();
        return ebookPurchaseRepository.findAllByMemberIdOrderByPurchasedAtDesc(member.getId(), PageRequest.of(page, size))
                .map(PurchaseResponse::new);
    }

    @Transactional(readOnly = true)
    public int getVoiceModelTotalPage(int size) {
        Member member = verifyMember();
        return voiceModelPurchaseRepository.findAllByMemberIdOrderByPurchasedAtDesc(member.getId(), PageRequest.of(0, size)).getTotalPages();
    }

    @Transactional(readOnly = true)
    public int getEbookTotalPage(int size) {
        Member member = verifyMember();
        return ebookPurchaseRepository.findAllByMemberIdOrderByPurchasedAtDesc(member.getId(), PageRequest.of(0, size)).getTotalPages();
    }

    @Transactional(readOnly = true)
    public Boolean isVoiceModelPurchased(Long voiceModelId) {
        Member member = verifyMember();
        return voiceModelPurchaseRepository.findByVoiceModelIdAndMemberId(voiceModelId, member.getId()).isPresent();
    }

    @Transactional(readOnly = true)
    public Boolean isEbookPurchased(Long ebookId) {
        Member member = verifyMember();
        return ebookPurchaseRepository.findByEbookIdAndMemberId(ebookId, member.getId()).isPresent();
    }

    public Member verifyMember() {
        return memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());
    }
}
