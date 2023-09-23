package com.example.YICcapstone.global.config;

import com.example.YICcapstone.global.handler.login.LoginFailureHandler;
import com.example.YICcapstone.global.handler.login.LoginSuccessJWTProvideHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors().disable() // 리액트 통신할 때 able하고 처리 과정 필요 -> 후에 찾아보기
                //.httpBasic().disable() //httpBasic 인증방법 비활성화(특정 리소스에 접근할 때 username과 password 물어봄) -> 컨트롤러 일단 수정 후에 사용
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .securityMatcher("/api/**") // 시큐리티가 적용되는 URL /api/**을 통해 /api/로 시작하는 모든 활동은 시큐리티 적용. 단, /api/가 붙지않고 시작하는 URL은 적용 X(프론트)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/member/sign-up").permitAll() // 모든 ROLE 사용자가 시큐리티를 사용하지 않아도 허용하는 부분
                        .anyRequest().authenticated() // 위의 .requestMatchers를 제외한 나머지 모든 URL이동(API요청)은 시큐리티 적용 X
                ) // .permitAll() , .hasRole("ADMIN") , .hasRole("USER") , .access("hasRole('ADMIN') or hasRole('USER')") : ADMIN과 USER는 enum 클래스로 사용자가 직접 설정한 역할
                .addFilterAfter(jsonUsernamePasswordLoginFilter(), LogoutFilter.class); // 기존 필터에 원하는 옵션을 추가한 필터(Json API로 적용)

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){//1 - PasswordEncoder 등록
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {//2 - AuthenticationManager 등록
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();//DaoAuthenticationProvider 사용
        provider.setPasswordEncoder(passwordEncoder());//PasswordEncoder로는 PasswordEncoderFactories.createDelegatingPasswordEncoder() 사용
        provider.setUserDetailsService(myUserDetailsService);
        return new ProviderManager(provider);
    }

    @Bean
    public LoginSuccessJWTProvideHandler loginSuccessJWTProvideHandler(){
        return new LoginSuccessJWTProvideHandler();
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
}