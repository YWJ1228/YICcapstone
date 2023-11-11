package com.example.YICcapstone.domain.member.dto;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.entity.Role;
import com.example.YICcapstone.domain.member.entity.Sex;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberInfoDto {
    private final String username;
    private final String name;
    private final String familyName;
    private final String givenName;
    private final String nickname;

    @Builder
    public MemberInfoDto(Member member) {
        this.username = member.getUsername();
        this.name = member.getName();
        this.familyName = member.getFamilyName();
        this.givenName = member.getGivenName();
        this.nickname = member.getNickname();
    }
}
