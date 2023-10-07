package com.example.YICcapstone.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MemberFindIdDto (@NotBlank(message = "이름을 입력해주세요.")
                               String name,
                               @NotBlank(message = "생년월일을 입력해주세요. ex)230101")
                               @Pattern(regexp = "[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1])", message = "생년월일은 6자리로 입력해주세요.")
                               String birth) {
}
