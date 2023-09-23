package com.example.YICcapstone.global.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors().disable() // 리액트 통신할 때 able하고 처리 과정 필요 -> 후에 찾아보기
                .securityMatcher("/api/**") // 시큐리티가 적용되는 URL /api/**을 통해 /api/로 시작하는 모든 활동은 시큐리티 적용. 단, /api/가 붙지않고 시작하는 URL은 적용 X(프론트)
                //.httpBasic().disable() //httpBasic 인증방법 비활성화(특정 리소스에 접근할 때 username과 password 물어봄) -> 컨트롤러 일단 수정 후에 사용
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/member/sign-up").permitAll() // 모든 ROLE 사용자가 시큐리티를 사용하지 않아도 허용하는 부분
                        .anyRequest().authenticated() // 위의 .requestMatchers를 제외한 나머지 모든 URL이동(API요청)은 시큐리티 적용 X
                ) // .permitAll() , .hasRole("ADMIN") , .hasRole("USER") , .access("hasRole('ADMIN') or hasRole('USER')") : ADMIN과 USER는 enum 클래스로 사용자가 직접 설정한 역할
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return httpSecurity.build();
    }
}