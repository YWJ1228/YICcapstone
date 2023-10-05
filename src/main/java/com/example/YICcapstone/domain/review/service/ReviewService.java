package com.example.YICcapstone.domain.review.service;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.exception.EbookNotFoundException;
import com.example.YICcapstone.domain.ebook.repository.EbookRepository;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import com.example.YICcapstone.domain.purchase.domain.VoiceModelPurchase;
import com.example.YICcapstone.domain.purchase.repository.EbookPurchaseRepository;
import com.example.YICcapstone.domain.purchase.repository.VoiceModelPurchaseRepository;
import com.example.YICcapstone.domain.review.domain.EbookReview;
import com.example.YICcapstone.domain.review.domain.VoiceModelReview;
import com.example.YICcapstone.domain.review.dto.request.ReviewCreationRequest;
import com.example.YICcapstone.domain.review.exception.PurchaseNotExistException;
import com.example.YICcapstone.domain.review.exception.ReviewAlreadyExistException;
import com.example.YICcapstone.domain.review.exception.ReviewNotFoundException;
import com.example.YICcapstone.domain.review.repository.EbookReviewRepository;
import com.example.YICcapstone.domain.review.repository.VoiceModelReviewRepository;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.exception.VoiceModelNotFoundException;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReviewService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EbookPurchaseRepository ebookPurchaseRepository;
    @Autowired
    private VoiceModelPurchaseRepository voiceModelPurchaseRepository;
    @Autowired
    private EbookReviewRepository ebookReviewRepository;
    @Autowired
    private VoiceModelReviewRepository voiceModelReviewRepository;
    @Autowired
    private EbookRepository ebookRepository;
    @Autowired
    private VoiceModelRepository voiceModelRepository;

    @Transactional
    public void createEbookReview(Long ebookPurchaseId, Long ebookId, ReviewCreationRequest reviewCreationRequest) {
        //TODO: 로그인한 유저 검증
//        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        EbookPurchase savedEbookPurchase = ebookPurchaseRepository.findById(ebookPurchaseId)
                .orElseThrow(PurchaseNotExistException::new);
//        if (savedEbookPurchase.getMember() !=) {
//            throw new PurchaseNotExistException();
//        }

        Ebook savedEbook = ebookRepository.findById(ebookId)
                .orElseThrow(EbookNotFoundException::new);

        //이미 리뷰를 작성했거나 삭제한 경우 재작성 불가능.
        EbookReview savedEbookReview = ebookReviewRepository.findByEbookIdAndMemberId(ebookId, savedEbookPurchase.getMember().getId()) //TODO: 현재 로그인한 유저로 변경
                .orElse(null);
        if (savedEbookReview != null && savedEbookReview.getIsDeleted())
            throw new ReviewAlreadyExistException();

        EbookReview ebookReview = new EbookReview(
                savedEbookPurchase,
                savedEbook,
                savedEbookPurchase.getMember(), //TODO: 현재 로그인한 유저로 변경
                reviewCreationRequest
        );
        ebookReviewRepository.save(ebookReview);
    }

    @Transactional
    public void createVoiceModelReview(Long voiceModelPurchaseId, Long voiceModelId, ReviewCreationRequest reviewCreationRequest) {
        //TODO: 로그인한 유저 검증
        VoiceModelPurchase savedVoiceModelPurchase = voiceModelPurchaseRepository.findById(voiceModelPurchaseId)
                .orElseThrow(PurchaseNotExistException::new);
        //TODO: 유저가 구매한 음성 모델인지 검증
        VoiceModel savedVoiceModel = voiceModelRepository.findById(voiceModelId)
                .orElseThrow(VoiceModelNotFoundException::new);

        //이미 리뷰를 작성했거나 삭제한 경우 재작성 불가능.
        VoiceModelReview savedVoiceModelReview = voiceModelReviewRepository.findByVoiceModelIdAndMemberId(voiceModelId, savedVoiceModelPurchase.getMember().getId()) //TODO: 현재 로그인한 유저로 변경
                .orElse(null);
        if (savedVoiceModelReview != null && savedVoiceModelReview.getIsDeleted())
            throw new ReviewAlreadyExistException();

        VoiceModelReview voiceModelReview = new VoiceModelReview(
                savedVoiceModelPurchase,
                savedVoiceModel,
                savedVoiceModelPurchase.getMember(), //TODO: 현재 로그인한 유저로 변경
                reviewCreationRequest
        );
        voiceModelReviewRepository.save(voiceModelReview);
    }

    @Transactional
    public void updateEbookReview(Long ebookReviewId, ReviewCreationRequest reviewCreationRequest) {
        //TODO: 로그인한 유저 검증
        EbookReview ebookReview = ebookReviewRepository.findById(ebookReviewId)
                .orElseThrow(ReviewNotFoundException::new);

        ebookReview.setContent(reviewCreationRequest.getContent());
        ebookReview.setGrade(reviewCreationRequest.getGrade());
        ebookReview.setUpdatedAt(LocalDateTime.now());
    }

    @Transactional
    public void updateVoiceModelReview(Long voiceModelReviewId, ReviewCreationRequest reviewCreationRequest) {
        //TODO: 로그인한 유저 검증
        VoiceModelReview voiceModelReview = voiceModelReviewRepository.findById(voiceModelReviewId)
                .orElseThrow(ReviewNotFoundException::new);

        voiceModelReview.setContent(reviewCreationRequest.getContent());
        voiceModelReview.setGrade(reviewCreationRequest.getGrade());
        voiceModelReview.setUpdatedAt(LocalDateTime.now());
    }

    @Transactional
    public void deleteEbookReview(Long ebookReviewId) {
        //TODO: 로그인한 유저 검증
        EbookReview ebookReview = ebookReviewRepository.findById(ebookReviewId)
                .orElseThrow(ReviewNotFoundException::new);

        ebookReview.setIsDeleted(true);
    }

    @Transactional
    public void deleteVoiceModelReview(Long voiceModelReviewId) {
        //TODO: 로그인한 유저 검증
        VoiceModelReview voiceModelReview = voiceModelReviewRepository.findById(voiceModelReviewId)
                .orElseThrow(ReviewNotFoundException::new);

        voiceModelReview.setIsDeleted(true);
    }

    @Transactional
    public EbookReview getEbookReview(Long ebookReviewId) {
        //TODO: 로그인한 유저 검증
        return ebookReviewRepository.findById(ebookReviewId)
                .orElseThrow(ReviewNotFoundException::new);
    }

    @Transactional
    public VoiceModelReview getVoiceModelReview(Long voiceModelReviewId) {
        //TODO: 로그인한 유저 검증
        return voiceModelReviewRepository.findById(voiceModelReviewId)
                .orElseThrow(ReviewNotFoundException::new);
    }

}
