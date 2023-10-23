package com.example.YICcapstone.domain.member.dto;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.entity.Sex;
import jakarta.validation.constraints.*;

public record MemberSignUpDto (@Email(message = "이메일 형식을 확인해주세요.")
                               String username,
                               @NotBlank(message = "비밀번호를 입력해주세요.(8~20 자릿수)")
                               @Size(min = 8, max = 20, message = "비밀번호는 8~20자 내외로 입력해주세요.")
                               @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
                                       message = "비밀번호는 8~20자리까지 가능하며 최소 1개 이상의 알파벳, 숫자, 특수문자를 각각 포함합니다.")
                               String password,
                               @NotBlank(message = "이름을 입력해주세요.")
                               String name,
                               @NotBlank(message = "닉네임을 입력해주세요.(2~8 자릿수)")
                               @Size(min=2, max=8, message = "닉네임은 2~8자 내외로 입력해주세요.")
                               @Pattern(regexp = "[A-Za-z0-9가-힣]+", message = "닉네임은 특수문자를 포함할 수 없습니다.")
                               String nickname,
                               @NotBlank(message = "생년월일을 입력해주세요. ex)230101")
                               @Pattern(regexp = "[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1])", message = "생년월일은 6자리로 입력해주세요.")
                               String birth,
                               Sex sex) {
    public Member toEntity() {
        return Member.builder().
                username(username).password(password).name(name).nickname(nickname).birth(birth).sex(sex).build();
    }
}