package com.example.YICcapstone.service;

import com.example.YICcapstone.dto.MemberInfoDto;
import com.example.YICcapstone.dto.MemberLoginDto;
import com.example.YICcapstone.dto.MemberSignUpDto;
import com.example.YICcapstone.dto.MemberUpdateDto;
import com.example.YICcapstone.entity.Member;
import com.example.YICcapstone.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void signUp(MemberSignUpDto memberSignUpDto) throws Exception {
        Member member = memberSignUpDto.toEntity();
        member.addRole();
        if(memberRepository.findByEmail(memberSignUpDto.email()).isPresent()){
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        if(memberRepository.findByNickname(memberSignUpDto.email()).isPresent()){
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        memberRepository.save(member);
    }

    // 로그인용 테스트
    public Member login(MemberLoginDto memberLoginDto) throws Exception {
        Member findMember = memberRepository.findByEmail(memberLoginDto.email()).orElseThrow(() -> new Exception("회원이 없습니다"));
        if(!findMember.getPassword().equals(memberLoginDto.password())) {
            throw new Exception("비밀번호가 다릅니다!");
        }
        return findMember;
    }

    public void update(String email, MemberUpdateDto memberUpdateDto) throws Exception {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));

        if(memberUpdateDto.password() != "") {
            member.updatePassword(memberUpdateDto.password());
        }
        if(memberUpdateDto.nickname() != "") {
            member.updateNickname(memberUpdateDto.nickname());
        }

        //memberUpdateDto.nickname().ifPresent(member::updateNickname);
        //memberUpdateDto.password().ifPresent(member::updatePassword);
    }

    public void withdraw(String email, String checkPW) throws Exception {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));

        if(member.getPassword() != checkPW) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        memberRepository.delete(member);
    }

    /*

    // id를 받아와서 해당 회원의 정보를 조회하는 메서드
    MemberInfoDto getInfo(Long id) throws Exception {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new Exception("회원이 없습니다"));
        return new MemberInfoDto(findMember);
    }
    /*
    //
    MemberInfoDto getMyInfo() throws Exception {
        Member findMember = memberRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 없습니다"));
        return new MemberInfoDto(findMember);
    }
    */
}
