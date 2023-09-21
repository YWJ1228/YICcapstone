package com.example.YICcapstone.service;

import com.example.YICcapstone.dto.MemberLoginDto;
import com.example.YICcapstone.dto.MemberSignUpDto;
import com.example.YICcapstone.dto.MemberUpdateDto;
import com.example.YICcapstone.entity.Member;
import com.example.YICcapstone.entity.Role;
import com.example.YICcapstone.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    private MemberSignUpDto makeMemberSignUpDto() {
        return new MemberSignUpDto("test1@test.com", "1234",
                "test1", "testNick1", "19990101", 1);
    }
    @Test
    public void 회원가입_성공() throws Exception {
        //given
        MemberSignUpDto memberSignUpDto = makeMemberSignUpDto();

        //when
        memberService.signUp(memberSignUpDto);

        //then
        Member member = memberRepository.findByEmail(memberSignUpDto.email()).orElseThrow(() -> new Exception("회원이 없습니다"));

        assertThat(member.getId()).isNotNull();
        assertThat(member.getEmail()).isEqualTo(memberSignUpDto.email());
        assertThat(member.getPassword()).isEqualTo(memberSignUpDto.password());
        assertThat(member.getName()).isEqualTo(memberSignUpDto.name());
        assertThat(member.getNickname()).isEqualTo(memberSignUpDto.nickname());
        assertThat(member.getBirth()).isEqualTo(memberSignUpDto.birth());
        assertThat(member.getSex()).isEqualTo(memberSignUpDto.sex());
        assertThat(member.getRole()).isSameAs(Role.USER);
    }

    @Test
    public void 회원가입_아이디중복_실패() throws Exception {
        //given
        MemberSignUpDto memberSignUpDto1 = new MemberSignUpDto("same@test.com", "1234",
                "test1", "testNick1", "19990101", 1);
        memberService.signUp(memberSignUpDto1);

        MemberSignUpDto memberSignUpDto2 = new MemberSignUpDto("same@test.com","1234",
                "test2", "testNick2", "19990101", 1);

        //when
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto2));
    }

    @Test
    public void 회원가입_닉네임중복_실패() throws Exception {
        //given
        MemberSignUpDto memberSignUpDto1 = new MemberSignUpDto("test1@test.com", "1234",
                "test", "sameNick", "19990101", 1);
        memberService.signUp(memberSignUpDto1);

        MemberSignUpDto memberSignUpDto2 = new MemberSignUpDto("test2@test.com","1234",
                "test", "sameNick", "19990101", 1);

        //when
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto2));
    }

    @Test
    public void 회원가입_정보미기입_실패() throws Exception {
        //given
        MemberSignUpDto memberSignUpDto1 = new MemberSignUpDto(null, "1234",
                "test1", "testNick1", "19990101", 1);
        MemberSignUpDto memberSignUpDto2 = new MemberSignUpDto("test2@test.com",null,
                "test2", "testNick2", "19990101", 1);
        MemberSignUpDto memberSignUpDto3 = new MemberSignUpDto("test3@test.com","1234",
                null, "testNick3", "19990101", 1);
        MemberSignUpDto memberSignUpDto4 = new MemberSignUpDto("test4@test.com","1234",
                "test4", null, "19990101", 1);
        MemberSignUpDto memberSignUpDto5 = new MemberSignUpDto("test5@test.com","1234",
                "test5", "testNick5", null, 1);
        MemberSignUpDto memberSignUpDto6 = new MemberSignUpDto("test6@test.com","1234",
                "test6", "testNick6", "19990101", null);

        //when, then
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto1));
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto2));
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto3));
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto4));
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto5));
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpDto6));
    }

    @Test
    public void 회원수정_닉네임_비밀번호수정_변동없으면_기존유지_성공() throws Exception {
        //given
        MemberSignUpDto memberSignUpDto = new MemberSignUpDto("test@test.com", "origin",
                "test", "testNick", "19990101", 1);
        memberService.signUp(memberSignUpDto);

        //when
        String changedPassword = "";
        String changedNickname = "changeNick";
        MemberUpdateDto memberUpdateDto = new MemberUpdateDto(changedPassword, changedNickname);

        //then
        memberService.update(memberSignUpDto.email(), memberUpdateDto);
        Member findmember = memberRepository.findByEmail(memberSignUpDto.email())
                .orElseThrow(() -> new Exception("회원이 없습니다"));
        assertThat(findmember.getPassword()).isEqualTo("origin");
        assertThat(findmember.getNickname()).isEqualTo(changedNickname);
    }

    @Test
    public void 회원탈퇴_성공() throws Exception {
        //given
        MemberSignUpDto memberSignUpDto = new MemberSignUpDto("test@test.com", "1234",
                "test", "testNick", "19990101", 1);
        memberService.signUp(memberSignUpDto);

        //when
        memberService.withdraw(memberSignUpDto.email(), memberSignUpDto.password());

        //then
        assertThat(assertThrows(Exception.class,
                ()-> memberRepository.findByEmail(memberSignUpDto.email())
                        .orElseThrow(() -> new Exception("회원이 없습니다"))).getMessage())
                        .isEqualTo("회원이 없습니다");
    }

    @Test
    public void 회원탈퇴_확인비밀번호_일치하지_않음_실패() throws Exception {
        //given
        MemberSignUpDto memberSignUpDto = new MemberSignUpDto("test@test.com", "1234",
                "test", "testNick", "19990101", 1);
        memberService.signUp(memberSignUpDto);

        //when
        assertThat(assertThrows(Exception.class,
                () -> memberService.withdraw(memberSignUpDto.email(), "different_pw")).getMessage())
                .isEqualTo("비밀번호가 일치하지 않습니다.");
    }

    // 테스트용 로그인
    @Test
    public void 회원가입_후_로그인_성공() throws Exception {
        MemberSignUpDto memberSignUpDto = new MemberSignUpDto("test@test.com", "1234",
                "test", "testNick", "19990101", 1);
        memberService.signUp(memberSignUpDto);

        MemberLoginDto memberLoginDto = new MemberLoginDto("test@test.com", "1234");
        Member member = memberRepository.findByEmail(memberLoginDto.email()).orElseThrow(() -> new Exception("회원이 없습니다"));

        Member login_member = memberService.login(memberLoginDto);

        assertEquals(member, login_member);
    }


    @AfterEach
    private void after(){
        em.clear();
    }
}