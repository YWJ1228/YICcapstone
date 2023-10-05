package com.example.YICcapstone.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record UpdateNicknameDto(@NotBlank(message = "닉네임을 입력해주세요.(2~8 자릿수)")
                                @Size(min=2, max=8, message = "닉네임은 2~8자 내외로 입력해주세요.")
                                @Pattern(regexp = "[A-Za-z0-9가-힣]+", message = "닉네임은 특수문자를 포함할 수 없습니다.")
                                String nickname) {

}