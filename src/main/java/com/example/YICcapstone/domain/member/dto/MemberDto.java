package com.example.YICcapstone.domain.member.dto;

import com.example.YICcapstone.domain.member.Member;
import com.example.YICcapstone.domain.member.Role;
import com.example.YICcapstone.domain.member.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String email;
    private String name;
    private String nickname;
    private String birth;
    private Role role;
    private Sex sex;

    @Builder
    public MemberDto(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.birth = member.getBirth();
        this.role = member.getRole();
        this.sex = member.getSex();
    }
}