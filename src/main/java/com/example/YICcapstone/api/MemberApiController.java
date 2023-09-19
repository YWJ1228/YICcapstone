package com.example.YICcapstone.api;

import com.example.YICcapstone.dto.MemberDto;
import com.example.YICcapstone.entity.Member;
import com.example.YICcapstone.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberApiController {
    @Autowired
    private MemberService memberService;

    // POST
    @PostMapping("/api/sign-up") // 우준 : 회원가입 요청 URL + JSON(email, password, name, nickname, sex)
    public ResponseEntity<Member> signup(@RequestBody MemberDto dto) {
        Member memberInfo = memberService.create(dto);
        return (memberInfo != null) ?
                ResponseEntity.status(HttpStatus.OK).body(memberInfo) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //@GetMapping("/api/log-in") // 우준 : 로그인 요청 URL + JSON(eamil, password)

}