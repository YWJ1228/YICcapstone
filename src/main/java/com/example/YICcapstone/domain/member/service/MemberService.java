package com.example.YICcapstone.domain.member.service;

import com.example.YICcapstone.domain.member.dto.MemberSignUpDto;
import com.example.YICcapstone.domain.member.dto.UpdateNicknameDto;
import com.example.YICcapstone.domain.member.entity.Member;

import java.util.List;

public interface MemberService {
    public void signUp(MemberSignUpDto memberSignUpDto); // 회원가입 서비스
    public void dupUsername(String username); // 회원가입 중, 아이디(이메일) 중복 체크 서비스
    public void dupNickname(String nickname); // 회원가입 중, 닉네임 중복 체크 서비스
    public void updateNickname(UpdateNicknameDto updateNicknameDto) throws Exception; // 로그인 중, 닉네임 변경 서비스
    public void updatePassword(String checkPassword, String ChangePassword) throws Exception; // 로그인 중, 비밀번호 변경 서비스
    public void withdraw(String checkPassword) throws Exception; // 로그인 중, 회원 탈퇴 서비스
    public List<Member> index(); // 회원가입 되어 있는 모든 사용자 불러오기
}
