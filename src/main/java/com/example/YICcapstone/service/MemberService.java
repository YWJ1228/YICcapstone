package com.example.YICcapstone.service;

import com.example.YICcapstone.dto.MemberDto;
import com.example.YICcapstone.entity.Member;
import com.example.YICcapstone.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member create(MemberDto dto) { // 회원가입
        Member member = dto.toEntity();
        if(member.getId() != null) {
            return null;
        }
        return memberRepository.save(member);
    }

    //public Member find(MemberDto dto)
}
