package com.example.YICcapstone.domain.review.controller;

import com.example.YICcapstone.domain.review.dto.request.ReviewCreationRequest;
import com.example.YICcapstone.domain.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/ebook")
    public void createEbookReview(@PathVariable Long ebookPurchaseId, @PathVariable Long ebookId, @RequestBody ReviewCreationRequest reviewCreationRequest) {
        reviewService.createEbookReview(ebookPurchaseId, ebookId, reviewCreationRequest);
    }

    @PostMapping("/voice-model")
    public void createVoiceModelReview(@PathVariable Long voiceModelPurchaseId, @PathVariable Long voiceModelId, @RequestBody ReviewCreationRequest reviewCreationRequest) {
        reviewService.createVoiceModelReview(voiceModelPurchaseId, voiceModelId, reviewCreationRequest);
    }

    @PutMapping("/ebook/{ebookReviewId}")
    public void updateEbookReview(@PathVariable Long ebookReviewId, @RequestBody ReviewCreationRequest reviewCreationRequest) {
        reviewService.updateEbookReview(ebookReviewId, reviewCreationRequest);
    }

    @PutMapping("/voice-model/{voiceModelReviewId}")
    public void updateVoiceModelReview(@PathVariable Long voiceModelReviewId, @RequestBody ReviewCreationRequest reviewCreationRequest) {
        reviewService.updateVoiceModelReview(voiceModelReviewId, reviewCreationRequest);
    }

    @DeleteMapping("/ebook/{ebookReviewId}")
    public void deleteEbookReview(@PathVariable Long ebookReviewId) {
        reviewService.deleteEbookReview(ebookReviewId);
    }

    @DeleteMapping("/voice-model/{voiceModelReviewId}")
    public void deleteVoiceModelReview(@PathVariable Long voiceModelReviewId) {
        reviewService.deleteVoiceModelReview(voiceModelReviewId);
    }

    @GetMapping("/ebook/{ebookId}")
    public void getEbookReview(@PathVariable Long ebookId) {
        reviewService.getEbookReview(ebookId);
    }

    @GetMapping("/voice-model/{voiceModelId}")
    public void getVoiceModelReview(@PathVariable Long voiceModelId) {
        reviewService.getVoiceModelReview(voiceModelId);
    }
}
