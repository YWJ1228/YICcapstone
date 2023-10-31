package com.example.YICcapstone.domain.purchase.service;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.exception.MemberNotExistException;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import com.example.YICcapstone.domain.purchase.domain.VoiceModelPurchase;
import com.example.YICcapstone.domain.purchase.dto.request.ReviewRequest;
import com.example.YICcapstone.domain.purchase.exception.EbookPurchaseNotFoundException;
import com.example.YICcapstone.domain.purchase.exception.ReviewAlreadyWrittenException;
import com.example.YICcapstone.domain.purchase.exception.ReviewNotWrittenException;
import com.example.YICcapstone.domain.purchase.exception.VoiceModelPurchaseNotFoundException;
import com.example.YICcapstone.domain.purchase.repository.EbookPurchaseRepository;
import com.example.YICcapstone.domain.purchase.repository.VoiceModelPurchaseRepository;
import com.example.YICcapstone.global.util.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private VoiceModelPurchaseRepository voiceModelPurchaseRepository;
    @Autowired
    private EbookPurchaseRepository ebookPurchaseRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public void createVoiceModelReview(ReviewRequest reviewRequest) {
        Member member = verifyMember();
        VoiceModelPurchase savedVoiceModelPurchase = voiceModelPurchaseRepository.findByVoiceModelIdAndMemberId(reviewRequest.getItemId(), member.getId())
                .orElseThrow(VoiceModelPurchaseNotFoundException::new);
        if(savedVoiceModelPurchase.getCreatedAt() != null)
            throw new ReviewAlreadyWrittenException();
        savedVoiceModelPurchase.createReview(reviewRequest);
    }

    @Transactional
    public void createEbookReview(ReviewRequest reviewRequest) {
        Member member = verifyMember();
        EbookPurchase savedEbookPurchase = ebookPurchaseRepository.findByEbookIdAndMemberId(reviewRequest.getItemId(), member.getId())
                .orElseThrow(EbookPurchaseNotFoundException::new);
        if(savedEbookPurchase.getCreatedAt() != null)
            throw new ReviewAlreadyWrittenException();
        savedEbookPurchase.createReview(reviewRequest);
    }

    @Transactional
    public void updateVoiceModelReview(ReviewRequest reviewRequest) {
        Member member = verifyMember();
        VoiceModelPurchase savedVoiceModelPurchase = voiceModelPurchaseRepository.findByVoiceModelIdAndMemberId(reviewRequest.getItemId(), member.getId())
                .orElseThrow(VoiceModelPurchaseNotFoundException::new);
        if(savedVoiceModelPurchase.getCreatedAt() == null)
            throw new ReviewNotWrittenException();
        savedVoiceModelPurchase.updateReview(reviewRequest);
    }

    @Transactional
    public void updateEbookReview(ReviewRequest reviewRequest) {
        Member member = verifyMember();
        EbookPurchase savedEbookPurchase = ebookPurchaseRepository.findByEbookIdAndMemberId(reviewRequest.getItemId(), member.getId())
                .orElseThrow(EbookPurchaseNotFoundException::new);
        if(savedEbookPurchase.getCreatedAt() == null)
            throw new ReviewNotWrittenException();
        savedEbookPurchase.updateReview(reviewRequest);
    }

    @Transactional
    public void deleteVoiceModelReview(Long purchaseId) {
        Member member = verifyMember();
        VoiceModelPurchase savedVoiceModelPurchase = voiceModelPurchaseRepository.findById(purchaseId)
                .orElseThrow(VoiceModelPurchaseNotFoundException::new);
        if(savedVoiceModelPurchase.getCreatedAt() != null)
            throw new ReviewNotWrittenException();
        savedVoiceModelPurchase.deleteReview();
    }

    @Transactional
    public void deleteEbookReview(Long purchaseId) {
        Member member = verifyMember();
        EbookPurchase savedEbookPurchase = ebookPurchaseRepository.findById(purchaseId)
                .orElseThrow(EbookPurchaseNotFoundException::new);
        if(savedEbookPurchase.getCreatedAt() != null)
            throw new ReviewNotWrittenException();
        savedEbookPurchase.deleteReview();
    }

    public Member verifyMember() {
        return memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());
    }
}
