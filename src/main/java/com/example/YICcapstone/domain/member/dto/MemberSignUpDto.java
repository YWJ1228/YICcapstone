package com.example.YICcapstone.domain.member.dto;

import com.example.YICcapstone.domain.member.Member;

public record MemberSignUpDto(String username, String password, String name, String nickname,
                              String birth, Integer sex) {

    public Member toEntity() {
        return Member.builder().username(username).password(password)
                .name(name).nickname(nickname).birth(birth).sex(sex).build();
    }
}
