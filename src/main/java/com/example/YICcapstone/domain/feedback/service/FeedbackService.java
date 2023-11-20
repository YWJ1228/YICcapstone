package com.example.YICcapstone.domain.feedback.service;

import com.example.YICcapstone.domain.feedback.dto.FeedbackDto;
import com.example.YICcapstone.domain.feedback.dto.FeedbackListDto;
import org.springframework.data.domain.Page;

public interface FeedbackService {
    void giveFeedback(FeedbackDto feedbackDto); // 피드백 요청(title, detail)
    Page<FeedbackListDto> showFeedback(int page, int size); // 모든 피드백 불러오기(관리자용)
    void deleteFeedback(Long id); // 피드백 삭제하기(관리자용)
}
