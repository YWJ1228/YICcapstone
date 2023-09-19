package com.example.YICcapstone.dto;

import com.example.YICcapstone.entity.Member;

public record MemberSignUpDto(String email, String password, String name, String nickname,
                              String birth, Integer sex) {

    public Member toEntity() {
        return Member.builder().email(email).password(password)
                .name(name).nickname(nickname).birth(birth).sex(sex).build();
    }
}
