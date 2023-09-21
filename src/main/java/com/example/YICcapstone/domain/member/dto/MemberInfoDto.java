package com.example.YICcapstone.domain.member.dto;

import com.example.YICcapstone.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberInfoDto {

    private final String username;
    private final String name;
    private final String nickname;
    private final String birth;
    private final Integer sex;

    @Builder
    public MemberInfoDto(Member member) {
        this.username = member.getUsername();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.birth = member.getBirth();
        this.sex = member.getSex();
    }
}
