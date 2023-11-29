package com.example.YICcapstone.domain.purchase.controller;

import com.example.YICcapstone.domain.purchase.dto.request.EbookReviewRequest;
import com.example.YICcapstone.domain.purchase.dto.request.VoiceModelReviewRequest;
import com.example.YICcapstone.domain.purchase.dto.response.ReviewNotWrittenResponse;
import com.example.YICcapstone.domain.purchase.dto.response.ReviewResponse;
import com.example.YICcapstone.domain.purchase.dto.response.ReviewWrittenResponse;
import com.example.YICcapstone.domain.purchase.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/api/review/voice-model")
    public ResponseEntity<String> createVoiceModelReview(@RequestBody @Valid VoiceModelReviewRequest voiceModelReviewRequest) {
        reviewService.createVoiceModelReview(voiceModelReviewRequest);
        return ResponseEntity.status(200).body("음성 모델 리뷰 작성 성공");
    }

    @PostMapping("/api/review/ebook")
    public ResponseEntity<String> createEbookReview(@RequestBody @Valid EbookReviewRequest ebookReviewRequest) {
        reviewService.createEbookReview(ebookReviewRequest);
        return ResponseEntity.status(200).body("Ebook 리뷰 작성 성공");
    }

    @PutMapping("/api/review/voice-model")
    public ResponseEntity<String> updateVoiceModelReview(@RequestBody @Valid VoiceModelReviewRequest voiceModelReviewRequest) {
        reviewService.updateVoiceModelReview(voiceModelReviewRequest);
        return ResponseEntity.status(200).body("음성 모델 리뷰 수정 성공");
    }

    @PutMapping("/api/review/ebook")
    public ResponseEntity<String> updateEbookReview(@RequestBody @Valid EbookReviewRequest ebookReviewRequest) {
        reviewService.updateEbookReview(ebookReviewRequest);
        return ResponseEntity.status(200).body("Ebook 리뷰 수정 성공");
    }

    @DeleteMapping("/api/review/voice-model")
    public ResponseEntity<String> deleteVoiceModelReview(@RequestParam Long purchaseId) {
        reviewService.deleteVoiceModelReview(purchaseId);
        return ResponseEntity.status(200).body("음성 모델 리뷰 삭제 성공");
    }

    @DeleteMapping("/api/review/ebook")
    public ResponseEntity<String> deleteEbookReview(@RequestParam Long purchaseId) {
        reviewService.deleteEbookReview(purchaseId);
        return ResponseEntity.status(200).body("Ebook 리뷰 삭제 성공");
    }

    @GetMapping("/review/voice-model")
    public Page<ReviewResponse> getVoiceModelReviewList(@RequestParam Long voiceModelId, @RequestParam int page, @RequestParam int size) {
        return reviewService.getVoiceModelReviewList(voiceModelId, page, size);
    }

    @GetMapping("/review/ebook")
    public Page<ReviewResponse> getEbookReviewList(@RequestParam Long ebookId, @RequestParam int page, @RequestParam int size) {
        return reviewService.getEbookReviewList(ebookId, page, size);
    }

    @GetMapping("/api/review/voice-model/not-written")
    public Page<ReviewNotWrittenResponse> getVoiceModelReviewListNotWritten(@RequestParam int page, @RequestParam int size) {
        return reviewService.getVoiceModelReviewListNotWritten(page, size);
    }

    @GetMapping("/api/review/ebook/not-written")
    public Page<ReviewNotWrittenResponse> getEbookReviewListNotWritten(@RequestParam int page, @RequestParam int size) {
        return reviewService.getEbookReviewListNotWritten(page, size);
    }

    @GetMapping("/api/review/voice-model/written")
    public Page<ReviewWrittenResponse> getVoiceModelReviewListWritten(@RequestParam int page, @RequestParam int size) {
        return reviewService.getVoiceModelReviewListWritten(page, size);
    }

    @GetMapping("/api/review/ebook/written")
    public Page<ReviewWrittenResponse> getEbookReviewListWritten(@RequestParam int page, @RequestParam int size) {
        return reviewService.getEbookReviewListWritten(page, size);
    }

}
