package com.example.YICcapstone.controller;

import com.example.YICcapstone.dto.MemberLoginDto;
import com.example.YICcapstone.dto.MemberSignUpDto;
import com.example.YICcapstone.entity.Member;
import com.example.YICcapstone.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    // 테스트용 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<Member> signup(@RequestBody MemberSignUpDto memberSignUpDto) throws Exception {
        memberService.signUp(memberSignUpDto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // 테스트용 로그인
    @PostMapping("/log-in")
    public ResponseEntity<Member> login(@RequestBody MemberLoginDto memberLoginDto) throws Exception {
        Member member = memberService.login(memberLoginDto);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }
}
