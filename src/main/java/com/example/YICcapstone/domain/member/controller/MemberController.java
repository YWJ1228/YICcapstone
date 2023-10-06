package com.example.YICcapstone.domain.member.controller;

import com.example.YICcapstone.domain.member.dto.*;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.service.EmailService;
import com.example.YICcapstone.domain.member.service.MemberService;
import com.example.YICcapstone.global.error.exception.ErrorCode;
import com.example.YICcapstone.global.util.security.SecurityUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    private final EmailService emailService;

    @PostMapping(value = "/api/sign-up") // 회원가입 요청 MemberSignUpDTO(username,password,name,nickname,birth,sex)
    public ResponseEntity<String> signUp(@Valid @RequestBody MemberSignUpDto memberSignUpDto) {
        memberService.signUp(memberSignUpDto);

        return ResponseEntity.status(HttpStatus.OK).body("회원가입 완료!");
    }

    // @PostMapping("/api/log-in") // 로그인 요청(username,password)
    // 해당 요청은 스프링 시큐리티로 구현되었으므로 해당 컨트롤러에 존재 X

    @GetMapping("/api/sign-up/username/{username}") // 회원가입 진행 중, 아이디(이메일) 중복 체크 하고 중복 없으면 인증코드 발송
    public ResponseEntity<String> dupUsername(@Email @PathVariable String username) throws Exception {
        memberService.dupUsername(username);

        String code = emailService.sendSimpleMessage(username);
        log.info("인증코드: " + code);

        return ResponseEntity.status(HttpStatus.OK).body(code);
    }

    @GetMapping("/api/sign-up/nickname/{nickname}") // 회원가입 진행 중, 닉네임 중복 체크
    public ResponseEntity<String> dupNickname(@NotBlank @Size(min=2, max=8) @Pattern(regexp = "[A-Za-z0-9가-힣]+")
                                                  @PathVariable("nickname") String nickname) {
        memberService.dupNickname(nickname);

        return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 닉네임입니다!");
    }

    @PatchMapping("/api/user/nickname") // 로그인 중, 닉네임 변경 요청 (nickname)
    public ResponseEntity<String> updateNickname(@Valid @RequestBody UpdateNicknameDto updateNicknameDto) {
        memberService.updateNickname(updateNicknameDto);

        return ResponseEntity.status(HttpStatus.OK).body("닉네임 변경 완료!");
    }

    @PatchMapping("/api/user/password") // 로그인 중, 비밀번호 변경 요청 (checkPassword, changePassword)
    public ResponseEntity<String> updatePassword(@Valid @RequestBody UpdatePasswordDto updatePasswordDto) {
        memberService.updatePassword(updatePasswordDto.checkPassword(), updatePasswordDto.changePassword(), SecurityUtil.getLoginUsername());

        return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경 완료!");
    }

    @DeleteMapping("/api/user") // 로그인 중, 회원 탈퇴 요청 (checkPassword)
    public ResponseEntity<String> withdraw(@Valid @RequestBody MemberWithdrawDto memberWithdrawDto) {
        memberService.withdraw(memberWithdrawDto.checkPassword(), SecurityUtil.getLoginUsername());

        return ResponseEntity.status(HttpStatus.OK).body("회원 탈퇴 완료!");
    }

    @PostMapping("/api/find/id") // 아이디 찾기 요청 (name, birth)
    public ResponseEntity<List<String>> findId(@Valid @RequestBody MemberFindIdDto memberFindIdDto) {
        List<String> usernames = memberService.findId(memberFindIdDto);

        return ResponseEntity.status(HttpStatus.OK).body(usernames);
    }
    @PostMapping("/api/find/password") // 비밀번호 찾기(변경) 위한 이메일 인증 요청 (username, name)
    public ResponseEntity<String> findPassword(@Valid @RequestBody MemberFindPasswordDto memberFindPasswordDto) throws Exception {
        memberService.findPassword(memberFindPasswordDto);

        String code = emailService.sendSimpleMessage(memberFindPasswordDto.username());
        log.info("인증코드: " + code);

        return ResponseEntity.status(HttpStatus.OK).body(code);
    }
    @PatchMapping("/api/find/password") // 비밀번호 찾기 이메일 인증 성공 후, 비밀번호 변경 요청 (username, changePassword)
    public ResponseEntity<String> newPassword(@Valid @RequestBody UpdateNewPasswordDto updateNewPasswordDto) {
        memberService.newPassword(updateNewPasswordDto);

        return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경 완료!");
    }


}