package com.example.YICcapstone.global.config;

import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.member.service.LoginService;
import com.example.YICcapstone.global.jwt.service.JwtService;
import com.example.YICcapstone.global.login.filter.JsonUsernamePasswordAuthenticationFilter;
import com.example.YICcapstone.global.jwt.filter.JwtAuthenticationProcessingFilter;
import com.example.YICcapstone.global.login.handler.LoginFailureHandler;
import com.example.YICcapstone.global.login.handler.LoginSuccessJWTProvideHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginService loginService;
    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors().disable() // 리액트 통신할 때 able하고 처리 과정 필요 -> 후에 찾아보기
                //.httpBasic().disable() //httpBasic 인증방법 비활성화(특정 리소스에 접근할 때 username과 password 물어봄) -> 컨트롤러 일단 수정 후에 사용
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .securityMatcher("/api/**") // /api/ 로 시작하는 모든 URL은 시큐리티 적용.
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/user/**").hasRole("USER") // /api/user/ 로 시작하는 모든 URL은 "USER" 권한만 요청 가능
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // /api/admin/ 로 시작하는 모든 URL은 "ADMIN" 권한만 요청 가능
                        .requestMatchers("/", "/api/sign-up/**", "/api/log-in/**").permitAll() // 모든 사용자가 시큐리티를 사용하지 않아도 허용하는 부분
                        .anyRequest().authenticated() // 위의 .requestMatchers를 제외한 나머지 URL 시큐리티 적용 X
                ) // .permitAll() , .hasRole("ADMIN") , .hasRole("USER") , .access("hasRole('ADMIN') or hasRole('USER')") : ADMIN과 USER는 enum 클래스로 사용자가 직접 설정한 역할
                .addFilterAfter(jsonUsernamePasswordLoginFilter(), LogoutFilter.class) // 기존 필터에 원하는 옵션을 추가한 필터(Json API로 적용)
                .addFilterBefore(jwtAuthenticationProcessingFilter(), JsonUsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(loginService);
        return new ProviderManager(provider);
    }

    @Bean
    public LoginSuccessJWTProvideHandler loginSuccessJWTProvideHandler(){
        return new LoginSuccessJWTProvideHandler(jwtService, memberRepository);//변경
    }

    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }

    @Bean
    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordLoginFilter(){
        JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordLoginFilter = new JsonUsernamePasswordAuthenticationFilter(objectMapper);
        jsonUsernamePasswordLoginFilter.setAuthenticationManager(authenticationManager());
        jsonUsernamePasswordLoginFilter.setAuthenticationSuccessHandler(loginSuccessJWTProvideHandler());
        jsonUsernamePasswordLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return jsonUsernamePasswordLoginFilter;
    }

    @Bean
    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter(){
        JwtAuthenticationProcessingFilter jsonUsernamePasswordLoginFilter = new JwtAuthenticationProcessingFilter(jwtService, memberRepository);

        return jsonUsernamePasswordLoginFilter;
    }
}