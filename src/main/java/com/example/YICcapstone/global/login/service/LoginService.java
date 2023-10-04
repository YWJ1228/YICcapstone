package com.example.YICcapstone.global.login.service;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

   private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 아이디입니다."));

        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRole().name())
                //.roles(String.valueOf(member.getRole()))
                .build();
    }
}
/*
  DaoAuthenticationProvider에서 전달해 준 Authentication 객체의 credentials(로그인 요청으로 온 비밀번호 정보를 암호화한 것)와
  DB에 존재하는 회원의 password의 일치여부를 UserDetailsService에서 제공하는 UserDetatils의 loadUserByUsername을 이용하여 비교
*/
