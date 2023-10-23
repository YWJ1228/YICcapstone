package com.example.YICcapstone.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateNewPasswordDto(@Email(message = "이메일 형식을 확인해주세요.")
                                   String username,
                                   @NotBlank(message = "비밀번호를 입력해주세요.(8~20 자릿수)")
                                   @Size(min = 8, max = 20, message = "비밀번호는 8~20자 내외로 입력해주세요.")
                                   @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
                                           message = "비밀번호는 8~20자리까지 가능하며 최소 1개 이상의 알파벳, 숫자, 특수문자를 각각 포함합니다.")
                                   String changePassword) {
}
