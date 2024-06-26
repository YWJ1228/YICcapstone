package com.example.YICcapstone.domain.purchase.service;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.exception.MemberNotExistException;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import com.example.YICcapstone.domain.purchase.domain.VoiceModelPurchase;
import com.example.YICcapstone.domain.purchase.dto.request.EbookReviewRequest;
import com.example.YICcapstone.domain.purchase.dto.request.VoiceModelReviewRequest;
import com.example.YICcapstone.domain.purchase.dto.response.ReviewNotWrittenResponse;
import com.example.YICcapstone.domain.purchase.dto.response.ReviewResponse;
import com.example.YICcapstone.domain.purchase.dto.response.ReviewWrittenResponse;
import com.example.YICcapstone.domain.purchase.exception.EbookPurchaseNotFoundException;
import com.example.YICcapstone.domain.purchase.exception.ReviewAlreadyWrittenException;
import com.example.YICcapstone.domain.purchase.exception.ReviewNotWrittenException;
import com.example.YICcapstone.domain.purchase.exception.VoiceModelPurchaseNotFoundException;
import com.example.YICcapstone.domain.purchase.repository.EbookPurchaseRepository;
import com.example.YICcapstone.domain.purchase.repository.VoiceModelPurchaseRepository;
import com.example.YICcapstone.global.error.exception.HandleAccessException;
import com.example.YICcapstone.global.util.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public void createVoiceModelReview(VoiceModelReviewRequest voiceModelReviewRequest) {
        Member member = verifyMember();
        VoiceModelPurchase savedVoiceModelPurchase = voiceModelPurchaseRepository.findByIdAndMemberId(voiceModelReviewRequest.getPurchaseId(), member.getId())
                .orElseThrow(VoiceModelPurchaseNotFoundException::new);
        if (savedVoiceModelPurchase.getCreatedAt() != null)
            throw new ReviewAlreadyWrittenException();
        savedVoiceModelPurchase.createReview(voiceModelReviewRequest);
    }

    @Transactional
    public void createEbookReview(EbookReviewRequest ebookReviewRequest) {
        Member member = verifyMember();
        EbookPurchase savedEbookPurchase = ebookPurchaseRepository.findByIdAndMemberId(ebookReviewRequest.getPurchaseId(), member.getId())
                .orElseThrow(EbookPurchaseNotFoundException::new);
        if (savedEbookPurchase.getCreatedAt() != null)
            throw new ReviewAlreadyWrittenException();
        savedEbookPurchase.createReview(ebookReviewRequest);
    }

    @Transactional
    public void updateVoiceModelReview(VoiceModelReviewRequest voiceModelReviewRequest) {
        Member member = verifyMember();
        VoiceModelPurchase savedVoiceModelPurchase = voiceModelPurchaseRepository.findByIdAndMemberId(voiceModelReviewRequest.getPurchaseId(), member.getId())
                .orElseThrow(VoiceModelPurchaseNotFoundException::new);
        if (savedVoiceModelPurchase.getCreatedAt() == null)
            throw new ReviewNotWrittenException();
        savedVoiceModelPurchase.updateReview(voiceModelReviewRequest);
    }

    @Transactional
    public void updateEbookReview(EbookReviewRequest ebookReviewRequest) {
        Member member = verifyMember();
        EbookPurchase savedEbookPurchase = ebookPurchaseRepository.findByIdAndMemberId(ebookReviewRequest.getPurchaseId(), member.getId())
                .orElseThrow(EbookPurchaseNotFoundException::new);
        if (savedEbookPurchase.getCreatedAt() == null)
            throw new ReviewNotWrittenException();
        savedEbookPurchase.updateReview(ebookReviewRequest);
    }

    @Transactional
    public void deleteVoiceModelReview(Long purchaseId) {
        Member member = verifyMember();
        VoiceModelPurchase savedVoiceModelPurchase = voiceModelPurchaseRepository.findById(purchaseId)
                .orElseThrow(VoiceModelPurchaseNotFoundException::new);
        if (!savedVoiceModelPurchase.getMember().getId().equals(member.getId()))
            throw new HandleAccessException();
        if (savedVoiceModelPurchase.getCreatedAt() == null)
            throw new ReviewNotWrittenException();
        savedVoiceModelPurchase.deleteReview();
    }

    @Transactional
    public void deleteEbookReview(Long purchaseId) {
        Member member = verifyMember();
        EbookPurchase savedEbookPurchase = ebookPurchaseRepository.findById(purchaseId)
                .orElseThrow(EbookPurchaseNotFoundException::new);
        if (!savedEbookPurchase.getMember().getId().equals(member.getId()))
            throw new HandleAccessException();
        if (savedEbookPurchase.getCreatedAt() == null)
            throw new ReviewNotWrittenException();
        savedEbookPurchase.deleteReview();
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponse> getVoiceModelReviewList(Long voiceModelId, int page, int size) {
        return voiceModelPurchaseRepository.findAllByVoiceModelIdAndContentIsNotNullOrderByCreatedAtDesc(voiceModelId, PageRequest.of(page, size))
                .map(ReviewResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponse> getEbookReviewList(Long ebookId, int page, int size) {
        return ebookPurchaseRepository.findAllByEbookIdAndContentIsNotNullOrderByCreatedAtDesc(ebookId, PageRequest.of(page, size))
                .map(ReviewResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<ReviewNotWrittenResponse> getVoiceModelReviewListNotWritten(int page, int size) {
        return voiceModelPurchaseRepository.findAllByMemberIdAndContentIsNullOrderByPurchasedAtDesc(verifyMember().getId(), PageRequest.of(page, size))
                .map(ReviewNotWrittenResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<ReviewNotWrittenResponse> getEbookReviewListNotWritten(int page, int size) {
        return ebookPurchaseRepository.findAllByMemberIdAndContentIsNullOrderByPurchasedAtDesc(verifyMember().getId(), PageRequest.of(page, size))
                .map(ReviewNotWrittenResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<ReviewWrittenResponse> getVoiceModelReviewListWritten(int page, int size) {
        return voiceModelPurchaseRepository.findAllByMemberIdAndContentIsNotNullOrderByPurchasedAtDesc(verifyMember().getId(), PageRequest.of(page, size))
                .map(ReviewWrittenResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<ReviewWrittenResponse> getEbookReviewListWritten(int page, int size) {
        return ebookPurchaseRepository.findAllByMemberIdAndContentIsNotNullOrderByPurchasedAtDesc(verifyMember().getId(), PageRequest.of(page, size))
                .map(ReviewWrittenResponse::new);
    }

    public Member verifyMember() {
        return memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());
    }
}
