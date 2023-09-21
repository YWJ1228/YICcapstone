package com.example.YICcapstone.domain.member.controller;

import com.example.YICcapstone.domain.member.Member;
import com.example.YICcapstone.domain.member.service.EmailService;
import com.example.YICcapstone.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final EmailService emailService;

    // 회원 가입 시, 이메일 인증 코드 발송
    @PostMapping("/login/mailConfirm")
    @ResponseBody
    public String mailConfirm(@RequestParam String email) throws Exception {
        String code = emailService.sendSimpleMessage(email);
        log.info("인증코드 : " + code);
        return code;
    }
}
