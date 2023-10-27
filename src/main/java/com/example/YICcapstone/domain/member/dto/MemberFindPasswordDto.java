package com.example.YICcapstone.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MemberFindPasswordDto(@Email(message = "이메일 형식을 확인해주세요.")
                                    String username,
                                    @NotBlank(message = "이름을 입력해주세요.")
                                    String name) {
}
