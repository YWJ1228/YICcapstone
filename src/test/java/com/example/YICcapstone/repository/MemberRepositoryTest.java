package com.example.YICcapstone.repository;

import com.example.YICcapstone.entity.Member;
import com.example.YICcapstone.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Member member = Member.builder().email("test1@test.com").password("1234").name("testName").nickname("testNick").role(Role.USER).build();

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
        Member member = Member.builder().password("1234").name("testName").nickname("testNick").role(Role.USER).build();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }
    @Test
    public void 회원가입시_이름이_없으면_실패() throws Exception {
        //given
        Member member = Member.builder().email("test1@test.com").password("1234").nickname("testNick").role(Role.USER).build();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }
    @Test
    public void 회원가입시_닉네임이_없으면_실패() throws Exception {
        //given
        Member member = Member.builder().email("test1@test.com").password("1234").role(Role.USER).build();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }
    @Test
    public void 회원가입시_중복아이디_존재하면_실패() throws Exception {
        //given
        Member member1 = Member.builder().email("same@test.com").password("1234").name("test1").nickname("testNick1").role(Role.USER).build();
        Member member2 = Member.builder().email("same@test.com").password("5678").name("test2").nickname("testNick1").role(Role.USER).build();

        memberRepository.save(member1);

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member2));
    }
    @Test
    public void 회원수정_성공() throws Exception {
        //given
        Member member1 = Member.builder().email("test@test.com").password("1234").name("test1").nickname("testnick1").role(Role.USER).build();
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
        assertThat(findUpdateMember.getNickname()).isEqualTo(updateNick);
    }
    @Test
    public void 회원삭제_성공() throws Exception {
        //given
        Member member1 = Member.builder().email("test1@test.com").password("1234").name("test1").nickname("testNick1").role(Role.USER).build();
        memberRepository.save(member1);

        //when
        memberRepository.delete(member1);

        //then
        assertThrows(Exception.class, () -> memberRepository.findById(member1.getId()).orElseThrow(() -> new Exception()));
    }
    @Test
    public void existByEmail_아이디중복확인_작동() throws Exception {
        //given
        String existEmail = "test@test.com";
        Member member1 = Member.builder().email(existEmail).password("1234").name("test1").nickname("testNick1").role(Role.USER).build();
        memberRepository.save(member1);

        //when, then
        assertThat(memberRepository.existsByEmail(existEmail)).isTrue();
        assertThat(memberRepository.existsByEmail(existEmail+"(another_email)")).isFalse();
    }
    @Test
    public void findByEmail_아이디_이용하여_찾기_작동() throws Exception {
        //given
        String existEmail = "test@test.com";
        Member member1 = Member.builder().email(existEmail).password("1234").name("test1").nickname("testNick1").role(Role.USER).build();
        memberRepository.save(member1);

        //when, then
        assertThat(memberRepository.findByEmail(existEmail).get().getName()).isEqualTo(member1.getName());
        assertThat(memberRepository.findByEmail(existEmail).get().getNickname()).isEqualTo(member1.getNickname());
        assertThrows(Exception.class,
                () -> memberRepository.findByEmail(existEmail+"(another_email)")
                        .orElseThrow(() -> new Exception()));
    }

    @Test
    public void 회원가입시_생성시간_등록_정상() throws Exception {
        //given
        Member member1 = Member.builder().email("test1.test.com").password("1234").name("test1").nickname("testNick1").role(Role.USER).build();
        memberRepository.save(member1);

        //when
        Member findMember = memberRepository.findById(member1.getId()).orElseThrow(() -> new Exception());

        //then
        assertThat(findMember.getCreatedDate()).isNotNull();
        assertThat(findMember.getLastModifiedDate()).isNotNull();
    }

    @AfterEach
    private void after(){
        em.clear();
    }
}