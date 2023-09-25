package com.example.YICcapstone.domain.member.service;

import com.example.YICcapstone.domain.member.dto.MemberSignUpDto;
import com.example.YICcapstone.domain.member.dto.UpdateNicknameDto;
import com.example.YICcapstone.domain.member.entity.Member;

public interface MemberService {
    public boolean signUp(MemberSignUpDto memberSignUpDto); // 회원가입 서비스
    public Boolean dupUsername(String username); // 회원가입 중, 아이디(이메일) 중복 체크 서비스
    public Boolean dupNickname(String nickname); // 회원가입 중, 닉네임 중복 체크 서비스
    public String updateNickname(UpdateNicknameDto updateNicknameDto); // 닉네임 변경 서비스
}
