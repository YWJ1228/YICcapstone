package com.example.YICcapstone.domain.purchase.controller;

import com.example.YICcapstone.domain.purchase.dto.request.ReviewRequest;
import com.example.YICcapstone.domain.purchase.dto.response.ReviewResponse;
import com.example.YICcapstone.domain.purchase.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/voice-model")
    public ResponseEntity<String> createVoiceModelReview(@RequestBody @Valid ReviewRequest reviewRequest) {
        reviewService.createVoiceModelReview(reviewRequest);
        return ResponseEntity.status(200).body("음성 모델 리뷰 작성 성공");
    }

    @PostMapping("/ebook")
    public ResponseEntity<String> createEbookReview(@RequestBody @Valid ReviewRequest reviewRequest) {
        reviewService.createEbookReview(reviewRequest);
        return ResponseEntity.status(200).body("Ebook 리뷰 작성 성공");
    }

    @PutMapping("/voice-model")
    public ResponseEntity<String> updateVoiceModelReview(@RequestBody @Valid ReviewRequest reviewRequest) {
        reviewService.updateVoiceModelReview(reviewRequest);
        return ResponseEntity.status(200).body("음성 모델 리뷰 수정 성공");
    }

    @PutMapping("/ebook")
    public ResponseEntity<String> updateEbookReview(@RequestBody @Valid ReviewRequest reviewRequest) {
        reviewService.updateEbookReview(reviewRequest);
        return ResponseEntity.status(200).body("Ebook 리뷰 수정 성공");
    }

    @DeleteMapping("/voice-model")
    public ResponseEntity<String> deleteVoiceModelReview(@RequestParam Long purchaseId) {
        reviewService.deleteVoiceModelReview(purchaseId);
        return ResponseEntity.status(200).body("음성 모델 리뷰 삭제 성공");
    }

    @DeleteMapping("/ebook")
    public ResponseEntity<String> deleteEbookReview(@RequestParam Long purchaseId) {
        reviewService.deleteEbookReview(purchaseId);
        return ResponseEntity.status(200).body("Ebook 리뷰 삭제 성공");
    }

    @GetMapping("/voice-model")
    public Page<ReviewResponse> getVoiceModelReviewList(@RequestParam Long voiceModelId, @RequestParam int page, @RequestParam int size) {
        return reviewService.getVoiceModelReviewList(voiceModelId, page, size);
    }

    @GetMapping("/ebook")
    public Page<ReviewResponse> getEbookReviewList(@RequestParam Long ebookId, @RequestParam int page, @RequestParam int size) {
        return reviewService.getEbookReviewList(ebookId, page, size);
    }

    @GetMapping("/voice-model/total")
    public int getVoiceModelReviewTotalPage(@RequestParam Long voiceModelId, @RequestParam int size) {
        return reviewService.getVoiceModelReviewTotalPage(voiceModelId, size);
    }

    @GetMapping("/ebook/total")
    public int getEbookReviewTotalPage(@RequestParam Long ebookId, @RequestParam int size) {
        return reviewService.getEbookReviewTotalPage(ebookId, size);
    }
}
