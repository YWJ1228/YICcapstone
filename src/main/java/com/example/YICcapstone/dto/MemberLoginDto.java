package com.example.YICcapstone.dto;

import com.example.YICcapstone.entity.Member;
import lombok.Builder;
import lombok.Data;

// 테스트용 DTO
public record MemberLoginDto(String email, String password) {
}
