package com.example.YICcapstone.domain.member.repository;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.entity.Role;
import com.example.YICcapstone.domain.member.entity.Sex;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @AfterEach
    void after(){
        em.clear();
    }

    @Test
    public void 회원저장_성공() throws Exception {
        //given
        Member member = Member.builder().username("username").password("1234567890")
                .name("Member1").nickname("NickName1").birth("990101").sex(Sex.WOMAN).role(Role.USER)
                .build();

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(saveMember.getId()).orElseThrow(() -> new RuntimeException("저장된 회원이 없습니다"));//아직 예외 클래스를 만들지 않았기에 RuntimeException으로 처리하겠습니다.

        assertThat(findMember).isSameAs(saveMember);
        assertThat(findMember).isSameAs(member);
    }

    @Test
    public void 오류_회원가입시_아이디가_없음() throws Exception {
        //given
        Member member = Member.builder().password("1234567890")
                .name("Member1").nickname("NickName1").birth("990101").sex(Sex.WOMAN).role(Role.USER)
                .build();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void 오류_회원가입시_이름이_없음() throws Exception {
        //given
        Member member = Member.builder().username("username").password("1234567890")
                .nickname("NickName1").birth("990101").sex(Sex.WOMAN).role(Role.USER)
                .build();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void 오류_회원가입시_닉네임이_없음() throws Exception {
        //given
        Member member = Member.builder().username("username").password("1234567890")
                .name("Member1").birth("990101").sex(Sex.WOMAN).role(Role.USER)
                .build();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void 오류_회원가입시_생년월일이_없음() throws Exception {
        //given
        Member member = Member.builder().username("username").password("1234567890")
                .name("Member1").nickname("NickName1").sex(Sex.WOMAN).role(Role.USER)
                .build();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void 오류_회원가입시_성별이_없음() throws Exception {
        //given
        Member member = Member.builder().username("username").password("1234567890")
                .name("Member1").nickname("NickName1").birth("990101").role(Role.USER)
                .build();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void 오류_회원가입시_중복된_아이디가_있음() throws Exception {
        //given
        Member member1 = Member.builder().username("username").password("1234567890")
                .name("Member1").nickname("NickName1").birth("990101").sex(Sex.WOMAN).role(Role.USER).build();
        Member member2 = Member.builder().username("username").password("1234567890")
                .name("Member1").nickname("NickName2").birth("990101").sex(Sex.WOMAN).role(Role.USER).build();

        memberRepository.save(member1);
        em.clear();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member2));

    }

    @Test
    public void 성공_회원수정() throws Exception {
        //given
        Member member1 = Member.builder().username("username").password("1234567890")
                .name("Member1").nickname("NickName1").birth("990101").sex(Sex.WOMAN).role(Role.USER).build();
        memberRepository.save(member1);
        em.clear();

        String updatePassword = "updatePassword";
        String updateNickName = "updateNickName";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //when
        Member findMember = memberRepository.findById(member1.getId()).orElseThrow(() -> new Exception());
        findMember.updateNickname(updateNickName);
        findMember.updatePassword(passwordEncoder,updatePassword);
        em.flush();

        //then
        Member findUpdateMember = memberRepository.findById(findMember.getId()).orElseThrow(() -> new Exception());

        assertThat(findUpdateMember).isSameAs(findMember);
        assertThat(passwordEncoder.matches(updatePassword, findUpdateMember.getPassword())).isTrue();
        assertThat(findUpdateMember.getNickname()).isNotEqualTo(member1.getNickname());
    }

    @Test
    public void 성공_회원삭제() throws Exception {
        //given
        Member member1 = Member.builder().username("username").password("1234567890")
                .name("Member1").nickname("NickName1").birth("990101").sex(Sex.MAN).role(Role.USER).build();
        memberRepository.save(member1);
        //em.clear();

        //when
        memberRepository.delete(member1);
        //em.clear();

        //then
        assertThrows(Exception.class,
                () -> memberRepository.findById(member1.getId())
                        .orElseThrow(() -> new Exception()));
    }


    @Test
    public void existByUsername_정상작동() throws Exception {
        //given
        String username = "username";
        Member member1 = Member.builder().username(username).password("1234567890")
                .name("Member1").nickname("NickName1").birth("990101").sex(Sex.WOMAN).role(Role.USER).build();
        memberRepository.save(member1);
        em.clear();

        //when, then
        assertThat(memberRepository.existsByUsername(username)).isTrue();
        assertThat(memberRepository.existsByUsername(username+"123")).isFalse();

    }

    @Test
    public void findByUsername_정상작동() throws Exception {
        //given
        String username = "username";
        Member member1 = Member.builder().username(username).password("1234567890")
                .name("Member1").nickname("NickName1").birth("990101").sex(Sex.WOMAN).role(Role.USER).build();
        memberRepository.save(member1);
        em.clear();

        //when, then
        assertThat(memberRepository.findByUsername(username).get().getUsername()).isEqualTo(member1.getUsername());
        assertThat(memberRepository.findByUsername(username).get().getName()).isEqualTo(member1.getName());
        assertThat(memberRepository.findByUsername(username).get().getId()).isEqualTo(member1.getId());
        assertThrows(Exception.class,
                () -> memberRepository.findByUsername(username+"123")
                        .orElseThrow(() -> new Exception()));

    }
    @Test
    public void 회원가입시_생성시간_등록() throws Exception {
        //given
        Member member1 = Member.builder().username("username").password("1234567890")
                .name("Member1").nickname("NickName1").birth("990101").sex(Sex.WOMAN).role(Role.USER).build();
        memberRepository.save(member1);
        em.clear();

        //when
        Member findMember = memberRepository.findById(member1.getId()).orElseThrow(() -> new Exception());

        //then
        assertThat(findMember.getCreatedAt()).isNotNull();
    }
}
