package com.example.YICcapstone.domain.feedback.service;

import com.example.YICcapstone.domain.feedback.dto.FeedbackDto;
import com.example.YICcapstone.domain.feedback.dto.FeedbackListDto;
import com.example.YICcapstone.domain.feedback.entity.Feedback;
import com.example.YICcapstone.domain.feedback.repository.FeedbackRepository;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.exception.MemberNotExistException;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.global.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final MemberRepository memberRepository;

    @Override
    public void giveFeedback(FeedbackDto feedbackDto) { // 피드백 요청(title, detail)
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());

        Feedback newFeedback = feedbackDto.toEntity();
        newFeedback.addUsername(SecurityUtil.getLoginUsername());
        newFeedback.setCreatedAt(LocalDateTime.now());

        feedbackRepository.save(newFeedback);
    }

    @Override
    public Page<FeedbackListDto> showFeedback(int page, int size) { // 모든 피드백 불러오기(관리자용)
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<FeedbackListDto> feedbackList = feedbackRepository.findAll(pageRequest)
                .map(FeedbackListDto::new);

        return feedbackList;
    }

    @Override
    public void deleteFeedback(Long id) { // 피드백 삭제하기(관리자용)
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());

        Feedback feedback = feedbackRepository.findById(id).orElse(null);

        if(feedback != null) {feedbackRepository.delete(feedback);}
    }
}
