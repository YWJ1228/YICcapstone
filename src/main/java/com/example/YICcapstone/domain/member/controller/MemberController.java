package com.example.YICcapstone.domain.member.controller;

import com.example.YICcapstone.domain.member.Member;
import com.example.YICcapstone.domain.member.dto.*;
import com.example.YICcapstone.domain.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping(value = "/api/member/sign-up") // 회원가입 요청(email,password,name,nickname,birth,sex)
    public ResponseEntity<String> signUp(@RequestBody @Valid MemberSignUpDto dto) {
        Boolean signup = memberService.signUp(dto);

        return (signup) ?
                ResponseEntity.status(HttpStatus.OK).body("회원가입이 정상적으로 완료되었습니다.") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 이메일이나 닉네임을 사용했습니다.");
    }

    @GetMapping("/api/member/sign-up/{nickname}") // 회원 가입 시, 닉네임 중복 체크(nickname)
    public ResponseEntity<String> dupNickname(@PathVariable String nickname) {
        Boolean result = memberService.dupNickname(nickname);
        return (!result) ?
                ResponseEntity.status(HttpStatus.OK).body("사용 가능한 닉네임입니다.") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 사용중인 닉네임입니다.");
    }

    @PostMapping("/api/member/log-in") // 로그인 요청(email,password)
    public ResponseEntity<Member> logIn(@RequestBody MemberLogInDto dto) {
        Member member = memberService.logIn(dto);

        return (member != null) ?
                ResponseEntity.status(HttpStatus.OK).body(member) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/member/log-in/id") // 아이디 찾기 인증 요청(name,birth)
    public ResponseEntity<String> findId(@RequestBody MemberFindIdDto dto) {
        String email = memberService.findId(dto);

        return (email != null) ?
                ResponseEntity.status(HttpStatus.OK).body(email) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("등록된 회원 정보가 없습니다.");
    }

    @PostMapping("/api/member/log-in/password") // 비밀번호 찾기 전, 인증 요청(name, email)
    public ResponseEntity<String> mailUpdatePw(@RequestBody MemberFindPwDto memberFindPwDto) {
        Boolean result = memberService.findPw(memberFindPwDto);

        return (result) ?
                ResponseEntity.status(HttpStatus.OK).body("회원정보를 확인했습니다.") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원정보가 존재하지 않습니다.");
    }

    @PatchMapping("/api/member/{id}/nickname") // 로그인 중, 닉네임 수정 요청
    public ResponseEntity<Member> updateNickname(@PathVariable Long id, @RequestBody UpdateNickname dto) {
        Member updated = memberService.updateNickname(id, dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/member/{id}/password") // 로그인 중, 비밀번호 수정 요청
    public ResponseEntity<Member> updatePassword(@PathVariable Long id, @RequestBody UpdatePassword dto) {
        Member updated = memberService.updatePassword(id, dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/member/{id}") // 로그인 중, 회원 탈퇴 요청
    public ResponseEntity<Member> deleteMember(@PathVariable Long id) {
        Member deleted = memberService.deteleMember(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
