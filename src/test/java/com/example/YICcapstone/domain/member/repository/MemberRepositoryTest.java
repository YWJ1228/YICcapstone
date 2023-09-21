package com.example.YICcapstone.domain.member.repository;

import com.example.YICcapstone.domain.member.Member;
import com.example.YICcapstone.domain.member.Role;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원저장_성공() throws Exception {
        //given
        Member member = Member.builder().username("test1@test.com").password("1234")
                .name("testName").nickname("testNick").birth("19990101").sex(1).role(Role.USER).build();

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(saveMember.getId()).orElseThrow(() -> new RuntimeException("저장된 회원이 없습니다"));

        assertThat(findMember).isSameAs(saveMember);
        assertThat(findMember).isSameAs(member);
    }
    @Test
    public void 회원가입시_아이디_겸_이메일이_없으면_실패() throws Exception {
        //given
        Member member = Member.builder().password("1234").name("testName").nickname("testNick")
                .birth("19990101").sex(1).role(Role.USER).build();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }
    @Test
    public void 회원가입시_이름이_없으면_실패() throws Exception {
        //given
        Member member = Member.builder().username("test1@test.com").password("1234")
                .birth("19990101").sex(1).nickname("testNick").role(Role.USER).build();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }
    @Test
    public void 회원가입시_닉네임이_없으면_실패() throws Exception {
        //given
        Member member = Member.builder().username("test1@test.com").password("1234")
                .birth("19990101").sex(1).role(Role.USER).build();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }
    @Test
    public void 회원가입시_중복아이디_존재하면_실패() throws Exception {
        //given
        Member member1 = Member.builder().username("same@test.com").password("1234")
                .birth("19990101").sex(1).name("test1").nickname("testNick1").role(Role.USER).build();
        Member member2 = Member.builder().username("same@test.com").password("5678")
                .birth("19990101").sex(1).name("test2").nickname("testNick1").role(Role.USER).build();

        memberRepository.save(member1);

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member2));
    }
    @Test
    public void 회원가입시_중복닉네임이면_실패() throws Exception {
        //given
        Member member1 = Member.builder().username("test1@test.com").password("1234")
                .birth("19990101").sex(1).name("test1").nickname("sameNick").role(Role.USER).build();
        Member member2 = Member.builder().username("test2@test.com").password("1234")
                .birth("19990101").sex(1).name("test2").nickname("sameNick").role(Role.USER).build();

        memberRepository.save(member1);

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member2));
    }
    @Test
    public void 회원수정_성공() throws Exception {
        //given
        Member member1 = Member.builder().username("test@test.com").password("1234")
                .birth("19990101").sex(1).name("test1").nickname("testnick1").role(Role.USER).build();
        memberRepository.save(member1);

        String updatePW = "updatepw";
        String updateNick = "updatenickname";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //when
        Member findMember = memberRepository.findById(member1.getId()).orElseThrow(() -> new Exception());
        findMember.updatePassword(passwordEncoder, updatePW);
        findMember.updateNickname(updateNick);

        //then
        Member findUpdateMember = memberRepository.findById(findMember.getId()).orElseThrow(() -> new Exception());

        assertThat(findUpdateMember).isSameAs(findMember);
        assertThat(passwordEncoder.matches(updatePW, findUpdateMember.getPassword())).isTrue();
        assertThat(findUpdateMember.getPassword()).isEqualTo(updatePW);
        assertThat(findUpdateMember.getNickname()).isEqualTo(updateNick);
    }
    @Test
    public void 회원삭제_성공() throws Exception {
        //given
        Member member1 = Member.builder().username("test1@test.com").password("1234")
                .birth("19990101").sex(1).name("test1").nickname("testNick1").role(Role.USER).build();
        memberRepository.save(member1);

        //when
        memberRepository.delete(member1);

        //then
        assertThrows(Exception.class, () -> memberRepository.findById(member1.getId()).orElseThrow(() -> new Exception()));
    }
    @Test
    public void existByEmail_아이디중복확인_작동() throws Exception {
        //given
        String existUsername = "test@test.com";
        Member member1 = Member.builder().username(existUsername).password("1234")
                .birth("19990101").sex(1).name("test1").nickname("testNick1").role(Role.USER).build();
        memberRepository.save(member1);

        //when, then
        assertThat(memberRepository.existsByUsername(existUsername)).isTrue();
        assertThat(memberRepository.existsByUsername(existUsername+"(another_email)")).isFalse();
    }
    @Test
    public void findByEmail_아이디_이용하여_찾기_작동() throws Exception {
        //given
        String existUsername = "test@test.com";
        Member member1 = Member.builder().username(existUsername).password("1234")
                .birth("19990101").sex(1).name("test1").nickname("testNick1").role(Role.USER).build();
        memberRepository.save(member1);

        //when, then
        assertThat(memberRepository.findByUsername(existUsername).get().getName()).isEqualTo(member1.getName());
        assertThat(memberRepository.findByUsername(existUsername).get().getNickname()).isEqualTo(member1.getNickname());
        assertThrows(Exception.class,
                () -> memberRepository.findByUsername(existUsername+"(another_email)")
                        .orElseThrow(() -> new Exception()));
    }

    @Test
    public void 회원가입시_생성시간_등록_정상() throws Exception {
        //given
        Member member1 = Member.builder().username("test1.test.com").password("1234")
                .birth("19990101").sex(1).name("test1").nickname("testNick1").role(Role.USER).build();
        memberRepository.save(member1);

        //when
        Member findMember = memberRepository.findById(member1.getId()).orElseThrow(() -> new Exception());

        //then
        assertThat(findMember.getCreated_at()).isNotNull();
        /*assertThat(findMember.getUpdated_at()).isNotNull();*/
    }

    @AfterEach
    private void after(){
        em.clear();
    }
}