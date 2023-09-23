package com.example.YICcapstone.global.config;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    //해당 클래스는 UserDetailsService에서 만든  User객체로, 해당 객체의 password와
    // JsonUsernamePasswordAuthenticationFilter(원 명칭 AbstractAuthenticationProcessingFilter의 DaoAuthenticationProvider)에서
    // request 정보를 통해 전달해준 UsernamePasswordAuthenticationToken(원 명칭 Authentication)의 Credentals를 비교하게 됨
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
