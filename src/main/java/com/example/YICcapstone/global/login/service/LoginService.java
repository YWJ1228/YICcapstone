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
                .build();
    }
}
/*
  JsonUsernamePasswordAuthenticationFilter 과정 중, 로그인 요청으로 만들어진 Authentication 객체의 credentials(password)와
  DB에 존재하는 회원의 password 일치여부를 DaoAuthenticationProvider에서 진행해 줌
  따라서 우리는 DB의 회원정보를 username을 매핑하여 가져오는 loadUserByUsername 사용 (UserDetailsService에서 User객체로 지원해줌)
  DaoAuthenticationProvider는 이것으로 비밀번호 일치여부를 판단하고, 결과에 따라 핸들러가 작동하게 됨.
*/
