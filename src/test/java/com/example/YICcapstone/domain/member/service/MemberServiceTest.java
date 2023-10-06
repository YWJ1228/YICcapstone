package com.example.YICcapstone.domain.member.service;

import com.example.YICcapstone.domain.member.dto.MemberSignUpDto;
import com.example.YICcapstone.domain.member.dto.UpdateNicknameDto;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.entity.Role;
import com.example.YICcapstone.domain.member.entity.Sex;
import com.example.YICcapstone.domain.member.exception.MemberUsernameDuplicatedException;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.global.util.security.SecurityUtil;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;

    private void clear() {
        em.flush();
        em.clear();
    }
    private MemberSignUpDto makeMemberSignUpDto() {
        return new MemberSignUpDto("test@test.com", "test1234@^^", "YIC", "YIC", "990101", Sex.MAN);
    }
    private MemberSignUpDto setMember() throws Exception {
        MemberSignUpDto memberSignUpDto = makeMemberSignUpDto();
        memberService.signUp(memberSignUpDto);
        clear();
        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();

        emptyContext.setAuthentication(new UsernamePasswordAuthenticationToken(User.builder()
                .username(memberSignUpDto.username())
                .password(memberSignUpDto.password())
                .roles(Role.USER.name())
                .build(),
                null, null));

        SecurityContextHolder.setContext(emptyContext);
        return memberSignUpDto;
    }
    @AfterEach
    public void removeMember(){
        SecurityContextHolder.createEmptyContext().setAuthentication(null);
    }

    @Test
    public void 회원가입_성공() { // 암호화 된 비밀번호를 제외, 모든 회원가입 정보가 DB에 정상적으로 등록되었는지 테스트
        MemberSignUpDto memberSignUpDto = makeMemberSignUpDto();

        //memberService.signUp(memberSignUpDto);
        memberService.signUp(memberSignUpDto);
        clear();

        Member member = memberRepository.findByUsername(memberSignUpDto.username()).orElse(null);
        assertThat(member.getId()).isNotNull();
        assertThat(member.getUsername()).isEqualTo(memberSignUpDto.username());
        assertThat(member.getName()).isEqualTo(memberSignUpDto.name());
        assertThat(member.getNickname()).isEqualTo(memberSignUpDto.nickname());
        assertThat(member.getBirth()).isEqualTo(memberSignUpDto.birth());
        assertThat(member.getSex()).isEqualTo(memberSignUpDto.sex());
        assertThat(member.getRole()).isSameAs(Role.USER);
    }

    @Test
    public void 회원가입_아이디존재_실패() { // 회원가입 후, 동일한 아이디(이메일)로 회원가입 요청 시 가입 X 테스트
        MemberSignUpDto memberSignUpDto1 =
                new MemberSignUpDto("test@test.com", "test1234@^^", "YIC", "YIC1", "990101", Sex.MAN);
        MemberSignUpDto memberSignUpDto2 =
                new MemberSignUpDto("test@test.com", "test1234@^^", "YIC", "YIC2", "990101", Sex.MAN);

        memberService.signUp(memberSignUpDto1);
        clear();

        assertThat(assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto2)));
    }

    @Test
    public void 회원가입_닉네임존재_실패() { // 회원가입 후, 동일한 닉네임으로 회원가입 요청 시 가입 X 테스트
        MemberSignUpDto memberSignUpDto1 =
                new MemberSignUpDto("test1@test.com", "test1234@^^", "YIC", "YIC", "990101", Sex.MAN);
        MemberSignUpDto memberSignUpDto2 =
                new MemberSignUpDto("test2@test.com", "test1234@^^", "YIC", "YIC", "990101", Sex.MAN);

        memberService.signUp(memberSignUpDto1);
        clear();

        assertThat(assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto2)));
    }

    @Test void 화원가입_입력누락된정보_실패() { // 회원가입 요청 시, 입력되지 않은 정보가 있다면 회원가입 X
        MemberSignUpDto memberSignUpDto0 =
                new MemberSignUpDto("test0@test.com", "test1234@^^", "YIC0", "YIC0", "990101", Sex.WOMAN);
        MemberSignUpDto memberSignUpDto1 =
                new MemberSignUpDto(null, "test1234@^^", "YIC", "YIC1", "990101", Sex.WOMAN);
        MemberSignUpDto memberSignUpDto2 =
                new MemberSignUpDto("test2@test.com", null, "YIC", "YIC2", "990101", Sex.WOMAN);
        MemberSignUpDto memberSignUpDto3 =
                new MemberSignUpDto("test3@test.com", "test1234@^^", null, "YIC3", "990101", Sex.WOMAN);
        MemberSignUpDto memberSignUpDto4 =
                new MemberSignUpDto("test4@test.com", "test1234@^^", "YIC", null, "990101", Sex.WOMAN);
        MemberSignUpDto memberSignUpDto5 =
                new MemberSignUpDto("test5@test.com", "test1234@^^", "YIC", "YIC5", null, Sex.WOMAN);
        MemberSignUpDto memberSignUpDto6 =
                new MemberSignUpDto("test6@test.com", "test1234@^^", "YIC", "YIC6", "990101", null);

        memberService.signUp(memberSignUpDto0); em.clear();
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto1)); em.clear();
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto2)); em.clear();
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto3)); em.clear();
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto4)); em.clear();
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto5)); em.clear();
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto6));
        /*
        assertThrows(DataIntegrityViolationException.class, () -> memberService.signUp(memberSignUpDto1)); em.clear();
        assertThrows(IllegalArgumentException.class, () -> memberService.signUp(memberSignUpDto2)); em.clear();
        assertThrows(DataIntegrityViolationException.class, () -> memberService.signUp(memberSignUpDto3)); em.clear();
        assertThrows(DataIntegrityViolationException.class, () -> memberService.signUp(memberSignUpDto4)); em.clear();
        assertThrows(DataIntegrityViolationException.class, () -> memberService.signUp(memberSignUpDto5)); em.clear();
        assertThrows(DataIntegrityViolationException.class, () -> memberService.signUp(memberSignUpDto6));
        // DataIntegrityViolationException는 hibernate exception으로 Entity Manager은 해당 exception 발생 시,
        // 세션을 초기화해주지 않음. 따라서 clear() 수동 호출 필요
        */
    }

    @Test
    public void 회원수정_닉네임수정_성공() throws Exception {
        MemberSignUpDto memberSignUpDto = setMember(); // setMember()를 통해 회원가입, 인증 통과 설정

        String updateNickName = "닉네임변경";
        memberService.updateNickname(new UpdateNicknameDto(updateNickName));
        clear();

        memberRepository.findByUsername(memberSignUpDto.username())
                .ifPresent((member -> {assertThat(member.getNickname()).isEqualTo(updateNickName);}));
    }

    @Test
    public void 회원수정_비밀번호수정_성공() throws Exception {
        MemberSignUpDto memberSignUpDto = setMember();

        String changePassword = "test5678@^^";
        memberService.updatePassword("test1234@^^", changePassword, memberSignUpDto.username());
        clear();

        Member findMember = memberRepository.findByUsername(memberSignUpDto.username()).orElseThrow(() -> new Exception());
        assertThat(findMember.matchPassword(passwordEncoder, changePassword)).isTrue();
    }

    @Test
    public void 회원탈퇴() throws Exception {
        MemberSignUpDto memberSignUpDto = setMember();

        memberService.withdraw("test1234@^^", memberSignUpDto.username());

        assertThat(assertThrows(Exception.class, ()-> memberRepository.findByUsername(memberSignUpDto.username()).orElseThrow(() -> new Exception("회원이 없습니다!")))
                .getMessage()).isEqualTo("회원이 없습니다!");
    }

    @Test
    public void 회원탈퇴_비밀번호_일치하지않음_실패() throws Exception {
        MemberSignUpDto memberSignUpDto = setMember();

        assertThrows(Exception.class, () -> memberService.withdraw("test1234@^^"+"1", memberSignUpDto.username()));
    }
}