package com.example.YICcapstone.domain.member.service;

import com.example.YICcapstone.domain.member.dto.*;
import com.example.YICcapstone.domain.member.Member;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Boolean signUp(MemberSignUpDto memberSignUpDto) { // 회원가입 서비스
        Member target = memberRepository.findByEmail(memberSignUpDto.email());
        if(target != null) return false;
        target = memberRepository.findByNickname(memberSignUpDto.nickname());
        if(target != null) return false;

        Member member = memberSignUpDto.toEntity();
        member.addRole();
        memberRepository.save(member);

        return true;
    }

    public Boolean dupNickname(String nickname) { // 회원가입 시, 아이디 중복 확인 서비스
        return memberRepository.existsByNickname(nickname);
    }

    public Member logIn(MemberLogInDto memberLoginDto) { // 로그인 서비스
        String email = memberLoginDto.email();
        String password = memberLoginDto.password();

        Member findMember = memberRepository.findByEmail(email);
        if(findMember != null && !findMember.getPassword().equals(password)) return null;

        return findMember;
    }

    public String findId(MemberFindIdDto memberFindIdDto) { // 아이디 찾기 서비스
        String name = memberFindIdDto.name();
        String birth = memberFindIdDto.birth();

        List<Member> userList = memberRepository.findByName(name);
        for(int i = 0; i < userList.size(); i++) {
            Member member = userList.get(i);
            if(member.getBirth().equals(birth)) {
                return member.getEmail();
            }
        }

        return null;
    }

    public Boolean findPw(MemberFindPwDto memberFindPwDto) { // 비밀번호 찾기 서비스
        String name = memberFindPwDto.name();
        String email = memberFindPwDto.email();

        Member target = memberRepository.findByEmail(email);
        if(target == null) return false;
        if(!target.getName().equals(name)) return false;

        return true;
    }

    public Member updateNickname(Long id, UpdateNickname updateNickname) { // 닉네임 변경 서비스
        Member target = memberRepository.findById(id).orElse(null);

        if(target == null) { return null; }

        target.updateNickname(updateNickname.nickname());
        Member updated = memberRepository.save(target);
        return updated;
    }

    public Member updatePassword(Long id, UpdatePassword updatePassword) { // 비밀번호 변경 서비스
        Member target = memberRepository.findById(id).orElse(null);

        if(target == null) { return null; }

        target.updatePassword(updatePassword.password());
        Member updated = memberRepository.save(target);
        return updated;
    }

    public Member deteleMember(Long id) { // 회원 삭제 서비스
        Member target = memberRepository.findById(id).orElse(null);
        if(target == null) return null;

        memberRepository.delete(target);
        return target;
    }
}
