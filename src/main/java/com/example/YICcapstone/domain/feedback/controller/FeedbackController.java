package com.example.YICcapstone.domain.feedback.controller;

import com.example.YICcapstone.domain.basket.dto.EbookBasketDto;
import com.example.YICcapstone.domain.feedback.dto.FeedbackDto;
import com.example.YICcapstone.domain.feedback.dto.FeedbackListDto;
import com.example.YICcapstone.domain.feedback.entity.Feedback;
import com.example.YICcapstone.domain.feedback.service.FeedbackService;
import com.example.YICcapstone.domain.member.dto.MemberSignUpDto;
import com.example.YICcapstone.domain.member.service.MemberService;
import com.example.YICcapstone.domain.purchase.dto.response.ReviewResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/api/user/feedback") // 피드백 요청(title, detail)
    public ResponseEntity<String> giveFeedback(@Valid @RequestBody FeedbackDto feedbackDto) {
        feedbackService.giveFeedback(feedbackDto);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 접수되었습니다!");
    }

    @GetMapping("/api/admin/feedback") // 모든 피드백 불러오기(관리자용)
    public ResponseEntity<Page<FeedbackListDto>> showFeedback(@RequestParam int page, @RequestParam int size) {
        Page<FeedbackListDto> feedbackList = feedbackService.showFeedback(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(feedbackList);
    }

    @DeleteMapping("/api/admin/feedback/{id}") // 피드백 삭제하기(관리자용)
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);

        return ResponseEntity.status(HttpStatus.OK).body("피드백이 삭제되었습니다!");
    }
}
