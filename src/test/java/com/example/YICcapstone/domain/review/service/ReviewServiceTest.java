package com.example.YICcapstone.domain.review.service;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.domain.EbookCategory;
import com.example.YICcapstone.domain.ebook.dto.request.EbookCreationRequest;
import com.example.YICcapstone.domain.ebook.repository.EbookCategoryRepository;
import com.example.YICcapstone.domain.ebook.repository.EbookRepository;
import com.example.YICcapstone.domain.member.dto.MemberSignUpDto;
import com.example.YICcapstone.domain.member.entity.Sex;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.member.service.MemberServiceImpl;
import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import com.example.YICcapstone.domain.purchase.domain.VoiceModelPurchase;
import com.example.YICcapstone.domain.purchase.repository.EbookPurchaseRepository;
import com.example.YICcapstone.domain.purchase.repository.VoiceModelPurchaseRepository;
import com.example.YICcapstone.domain.review.domain.EbookReview;
import com.example.YICcapstone.domain.review.domain.VoiceModelReview;
import com.example.YICcapstone.domain.review.dto.request.ReviewCreationRequest;
import com.example.YICcapstone.domain.review.repository.EbookReviewRepository;
import com.example.YICcapstone.domain.review.repository.VoiceModelReviewRepository;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModelCategory;
import com.example.YICcapstone.domain.voicemodel.dto.request.VoiceModelCreationRequest;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelCategoryRepository;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {
    @Autowired
    ReviewService reviewService;
    @Autowired
    EbookRepository ebookRepository;
    @Autowired
    VoiceModelRepository voiceModelRepository;
    @Autowired
    EbookCategoryRepository ebookCategoryRepository;
    @Autowired
    VoiceModelCategoryRepository voiceModelCategoryRepository;
    @Autowired
    EbookPurchaseRepository ebookPurchaseRepository;
    @Autowired
    VoiceModelPurchaseRepository voiceModelPurchaseRepository;
    @Autowired
    MemberServiceImpl memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EbookReviewRepository ebookReviewRepository;
    @Autowired
    VoiceModelReviewRepository voiceModelReviewRepository;
    @Autowired
    EntityManager em;
    Ebook testEbook;
    VoiceModel testVoiceModel;
    EbookPurchase testEbookPurchase;
    VoiceModelPurchase testVoiceModelPurchase;

    @BeforeEach
    void setUp() {
        MemberSignUpDto memberSignUpDto = new MemberSignUpDto(
                "test@naver.com",
                "test1234@^^",
                "test",
                "test",
                "990315",
                Sex.MAN
        );
        memberService.signUp(memberSignUpDto);
        ebookCategoryRepository.save(new EbookCategory("novel"));
        EbookCreationRequest ebookCreationRequest = new EbookCreationRequest(
                "title", "author",
                300, "publisher",
                10000, "image",
                "comment", "content", "novel");
        testEbook = ebookRepository.save(new Ebook(ebookCreationRequest));
        testEbookPurchase = ebookPurchaseRepository.save(new EbookPurchase(testEbook, memberRepository.findByUsername("test@naver.com").orElse(null)));

        voiceModelCategoryRepository.save(new VoiceModelCategory("actor"));
        VoiceModelCreationRequest voiceModelCreationRequest = new VoiceModelCreationRequest(
                "testUrl", "testName",
                3000, "testImageUrl",
                "testComment", "testSampleUrl",
                "actor");
        testVoiceModel = voiceModelRepository.save(new VoiceModel(voiceModelCreationRequest));
        testVoiceModelPurchase = voiceModelPurchaseRepository.save(new VoiceModelPurchase(testVoiceModel, memberRepository.findByUsername("test@naver.com").orElse(null)));
    }

    @Test
    @Transactional
    void ebook리뷰_생성_성공() {
        // 1. 예상 데이터
        ReviewCreationRequest reviewCreationRequest = new ReviewCreationRequest(
                "testComment", 5
        );
        reviewService.createEbookReview(testEbookPurchase.getId(), testEbook.getId(), reviewCreationRequest);
        // 2. 실제 데이터
        EbookReview ebookReview = ebookReviewRepository.findAll().get(0);
        // 3. 비교 및 검증
        assertThat(ebookReview).isNotNull();
        assertThat(ebookReview.getEbook()).isEqualTo(testEbook);
        assertThat(ebookReview.getEbookPurchase()).isEqualTo(testEbookPurchase);
        assertThat(ebookReview.getMember()).isEqualTo(memberRepository.findByUsername("test@naver.com").orElse(null));
        assertThat(ebookReview.getContent()).isEqualTo("testComment");
        assertThat(ebookReview.getGrade()).isEqualTo(5);
    }

    @Test
    @Transactional
    void voiceModel리뷰_생성_성공() {
        // 1. 예상 데이터
        ReviewCreationRequest reviewCreationRequest = new ReviewCreationRequest(
                "testComment", 5
        );
        reviewService.createVoiceModelReview(testVoiceModelPurchase.getId(), testVoiceModel.getId(), reviewCreationRequest);
        // 2. 실제 데이터
        VoiceModelReview voiceModelReview = voiceModelReviewRepository.findAll().get(0);
        // 3. 비교 및 검증
        assertThat(voiceModelReview).isNotNull();
        assertThat(voiceModelReview.getVoiceModel()).isEqualTo(testVoiceModel);
        assertThat(voiceModelReview.getVoiceModelPurchase()).isEqualTo(testVoiceModelPurchase);
        assertThat(voiceModelReview.getMember()).isEqualTo(memberRepository.findByUsername("test@naver.com").orElse(null));
        assertThat(voiceModelReview.getContent()).isEqualTo("testComment");
        assertThat(voiceModelReview.getGrade()).isEqualTo(5);
    }

    @Test
    @Transactional
    void ebook리뷰_수정_성공() {
        // 1. 예상 데이터
        ReviewCreationRequest reviewCreationRequest = new ReviewCreationRequest(
                "testComment", 5
        );
        reviewService.createEbookReview(testEbookPurchase.getId(), testEbook.getId(), reviewCreationRequest);
        ReviewCreationRequest reviewUpdateRequest = new ReviewCreationRequest(
                "updateComment", 3
        );
        reviewService.updateEbookReview(ebookReviewRepository.findAll().get(0).getId(), reviewUpdateRequest);
        // 2. 실제 데이터
        EbookReview ebookReview = ebookReviewRepository.findAll().get(0);
        // 3. 비교 및 검증
        assertThat(ebookReview).isNotNull();
        assertThat(ebookReview.getEbook()).isEqualTo(testEbook);
        assertThat(ebookReview.getEbookPurchase()).isEqualTo(testEbookPurchase);
        assertThat(ebookReview.getMember()).isEqualTo(memberRepository.findByUsername("test@naver.com").orElse(null));
        assertThat(ebookReview.getContent()).isEqualTo("updateComment");
        assertThat(ebookReview.getGrade()).isEqualTo(3);
    }

    @Test
    @Transactional
    void voiceModel리뷰_수정_성공() {
        // 1. 예상 데이터
        ReviewCreationRequest reviewCreationRequest = new ReviewCreationRequest(
                "testComment", 5
        );
        reviewService.createVoiceModelReview(testVoiceModelPurchase.getId(), testVoiceModel.getId(), reviewCreationRequest);
        ReviewCreationRequest reviewUpdateRequest = new ReviewCreationRequest(
                "updateComment", 3
        );
        reviewService.updateVoiceModelReview(voiceModelReviewRepository.findAll().get(0).getId(), reviewUpdateRequest);
        // 2. 실제 데이터
        VoiceModelReview voiceModelReview = voiceModelReviewRepository.findAll().get(0);
        // 3. 비교 및 검증
        assertThat(voiceModelReview).isNotNull();
        assertThat(voiceModelReview.getVoiceModel()).isEqualTo(testVoiceModel);
        assertThat(voiceModelReview.getVoiceModelPurchase()).isEqualTo(testVoiceModelPurchase);
        assertThat(voiceModelReview.getMember()).isEqualTo(memberRepository.findByUsername("test@naver.com").orElse(null));
        assertThat(voiceModelReview.getContent()).isEqualTo("updateComment");
        assertThat(voiceModelReview.getGrade()).isEqualTo(3);
    }

    @Test
    @Transactional
    void ebook리뷰_삭제_성공() {
        // 1. 예상 데이터
        ReviewCreationRequest reviewCreationRequest = new ReviewCreationRequest(
                "testComment", 5
        );
        reviewService.createEbookReview(testEbookPurchase.getId(), testEbook.getId(), reviewCreationRequest);
        // 2. 실제 데이터
        reviewService.deleteEbookReview(ebookReviewRepository.findAll().get(0).getId());
        EbookReview savedEbookReview = ebookReviewRepository.findAll().get(0);
        // 3. 비교 및 검증
        assertThat(savedEbookReview).isNotNull();
        assertThat(savedEbookReview.getIsDeleted()).isTrue();
    }

    @Test
    @Transactional
    void voiceModel리뷰_삭제_성공() {
        // 1. 예상 데이터
        ReviewCreationRequest reviewCreationRequest = new ReviewCreationRequest(
                "testComment", 5
        );
        reviewService.createVoiceModelReview(testVoiceModelPurchase.getId(), testVoiceModel.getId(), reviewCreationRequest);
        // 2. 실제 데이터
        reviewService.deleteVoiceModelReview(voiceModelReviewRepository.findAll().get(0).getId());
        VoiceModelReview savedVoiceModelReview = voiceModelReviewRepository.findAll().get(0);
        // 3. 비교 및 검증
        assertThat(savedVoiceModelReview).isNotNull();
        assertThat(savedVoiceModelReview.getIsDeleted()).isTrue();
    }

    @Test
    @Transactional
    void ebook리뷰_조회_성공() {
        // 1. 예상 데이터
        ReviewCreationRequest reviewCreationRequest = new ReviewCreationRequest(
                "testComment", 5
        );
        reviewService.createEbookReview(testEbookPurchase.getId(), testEbook.getId(), reviewCreationRequest);
        // 2. 실제 데이터
        EbookReview ebookReview = reviewService.getEbookReview(ebookReviewRepository.findAll().get(0).getId());
        // 3. 비교 및 검증
        assertThat(ebookReview).isNotNull();
        assertThat(ebookReview.getEbook()).isEqualTo(testEbook);
        assertThat(ebookReview.getEbookPurchase()).isEqualTo(testEbookPurchase);
        assertThat(ebookReview.getMember()).isEqualTo(memberRepository.findByUsername("test@naver.com").orElse(null));
        assertThat(ebookReview.getContent()).isEqualTo("testComment");
        assertThat(ebookReview.getGrade()).isEqualTo(5);
    }

    @Test
    @Transactional
    void voiceModel리뷰_조회_성공() {
        // 1. 예상 데이터
        ReviewCreationRequest reviewCreationRequest = new ReviewCreationRequest(
                "testComment", 5
        );
        reviewService.createVoiceModelReview(testVoiceModelPurchase.getId(), testVoiceModel.getId(), reviewCreationRequest);
        // 2. 실제 데이터
        VoiceModelReview voiceModelReview = reviewService.getVoiceModelReview(voiceModelReviewRepository.findAll().get(0).getId());
        // 3. 비교 및 검증
        assertThat(voiceModelReview).isNotNull();
        assertThat(voiceModelReview.getVoiceModel()).isEqualTo(testVoiceModel);
        assertThat(voiceModelReview.getVoiceModelPurchase()).isEqualTo(testVoiceModelPurchase);
        assertThat(voiceModelReview.getMember()).isEqualTo(memberRepository.findByUsername("test@naver.com").orElse(null));
        assertThat(voiceModelReview.getContent()).isEqualTo("testComment");
        assertThat(voiceModelReview.getGrade()).isEqualTo(5);
    }
}