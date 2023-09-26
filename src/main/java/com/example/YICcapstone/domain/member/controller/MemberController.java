package com.example.YICcapstone.domain.member.controller;

import com.example.YICcapstone.domain.member.dto.MemberSignUpDto;
import com.example.YICcapstone.domain.member.dto.UpdateNicknameDto;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.service.EmailService;
import com.example.YICcapstone.domain.member.service.MemberServiceImpl;
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

@Slf4j
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
public class MemberController {
    @Autowired
    private MemberServiceImpl memberService;

    private final EmailService emailService;

    @PostMapping(value = "/api/sign-up") // 회원가입 요청 MemberSignUpDTO(username,password,name,nickname,birth,sex)
    public ResponseEntity<String> signUp(@Valid @RequestBody MemberSignUpDto memberSignUpDto) {
        memberService.signUp(memberSignUpDto);

        return ResponseEntity.status(HttpStatus.OK).body("회원가입 완료!");
    }

    //@PostMapping("/api/log-in") // 로그인 요청(username,password)
    //해당 요청은 스프링 시큐리티로 구현되었으므로 해당 컨트롤러에 존재 X

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

    /*
    @PatchMapping("/api/user/nickname") // 로그인 중, 닉네임 수정 요청
    public ResponseEntity<String> updateNickname(@RequestBody UpdateNicknameDto updateNicknameDto) {
        String updated = memberService.updateNickname(updateNicknameDto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated + ", 닉네임 변경 완료!") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/member/log-in/id") // 아이디 찾기 인증 요청(name,birth)
    public ResponseEntity<String> findId(@RequestBody MemberFindIdDto dto) {
        String username = memberService.findId(dto);

        return (username != null) ?
                ResponseEntity.status(HttpStatus.OK).body(username) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("등록된 회원 정보가 없습니다.");
    }

    @PostMapping("/api/member/log-in/password") // 비밀번호 찾기 전, 인증 요청(name, username)
    public ResponseEntity<String> mailUpdatePw(@RequestBody MemberFindPwDto memberFindPwDto) {
        Boolean result = memberService.findPw(memberFindPwDto);

        return (result) ?
                ResponseEntity.status(HttpStatus.OK).body("회원정보를 확인했습니다.") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원정보가 존재하지 않습니다.");
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
    */
}
