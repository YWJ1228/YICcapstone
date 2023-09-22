package com.example.YICcapstone.domain.member.controller;

import com.example.YICcapstone.domain.member.Member;
import com.example.YICcapstone.domain.member.dto.MemberFindPwDto;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
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
public class EmailController {

    private final EmailService emailService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/api/email/sign-up/request") // 회원 가입 시, 이메일 인증 코드 발송
    public ResponseEntity<String> mailConfirmSignUp(@RequestParam String email) throws Exception {
        String code = emailService.sendSimpleMessage(email);
        log.info("인증코드 : " + code);
        return ResponseEntity.status(HttpStatus.OK).body(code);
    }

    @PostMapping("/api/email/password/request/{email}") // 변경된 예비 비밀번호 정보 이메일 발송
    public ResponseEntity<String> mailUpdatePw(@RequestParam String email) throws Exception {
        String code = emailService.sendSimpleMessage(email);
        log.info("인증코드 : " + code);

        Member target = memberRepository.findByEmail(email);
        if(target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원정보가 존재하지 않습니다.");
        }

        target.updatePassword(code);
        Member updated = memberRepository.save(target);

        return ResponseEntity.status(HttpStatus.OK).body("예비 비밀번호로 변경되었습니다. 메일을 확인해주세요.");
    }
}
