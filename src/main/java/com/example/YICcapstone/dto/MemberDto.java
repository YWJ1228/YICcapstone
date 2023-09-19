package com.example.YICcapstone.dto;

import com.example.YICcapstone.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private Integer sex;
    private LocalDateTime created_at;
    private Integer role;

    public Member toEntity(){ return new Member(id, email, password, name, nickname, sex, created_at, role=0);}
    // 우준 : 회원가입을 통한 사용자 등록은 관리자가 아닌 기본 회원이므로 role=0으로 기본 세팅
}