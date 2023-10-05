package com.example.YICcapstone.global.config;

import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.global.login.service.LoginService;
import com.example.YICcapstone.global.jwt.service.JwtService;
import com.example.YICcapstone.global.login.filter.JsonUsernamePasswordAuthenticationFilter;
import com.example.YICcapstone.global.jwt.filter.JwtAuthenticationProcessingFilter;
import com.example.YICcapstone.global.login.handler.LoginFailureHandler;
import com.example.YICcapstone.global.login.handler.LoginSuccessJWTProvideHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                //.cors().disable() // 리액트 통신할 때 able하고 처리 과정 필요 -> 후에 찾아보기
                //.httpBasic().disable() //httpBasic 인증방법 비활성화(특정 리소스에 접근할 때 username과 password 물어봄) -> 컨트롤러 일단 수정 후에 사용
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .securityMatcher("/api/**") // /api/ 로 시작하는 모든 URL은 시큐리티 적용.
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/user/**").hasRole("USER") // /api/user/ 로 시작하는 모든 URL은 "USER" 권한만 요청 가능
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // /api/admin/ 로 시작하는 모든 URL은 "ADMIN" 권한만 요청 가능
                        .requestMatchers("/", "/api/sign-up/**", "/api/log-in/**", "/api/find/**").permitAll() // 모든 사용자가 시큐리티를 사용하지 않아도 허용하는 부분
                        .anyRequest().authenticated() // 위의 .requestMatchers를 제외한 나머지 URL 시큐리티 적용 X
                ) // .permitAll() , .hasRole("ADMIN") , .hasRole("USER") , .access("hasRole('ADMIN') or hasRole('USER')") : ADMIN과 USER는 enum 클래스로 사용자가 직접 설정한 역할
                .addFilterAfter(jsonUsernamePasswordLoginFilter(), LogoutFilter.class) // 로그아웃 상태 이후에 로그인 필터 동작
                .addFilterBefore(jwtAuthenticationProcessingFilter(), JsonUsernamePasswordAuthenticationFilter.class); // Json 로그인 필터 전에 JWT 인증 필터 먼저 동작

        return httpSecurity.build();
    }
    /*
    * jsonUsernamePasswordLoginFilter() 필터란? + 수행할 역할은?
     - AbstractAuthenticationProcessingFilter를 extends하여 만든 필터
     - 인증의 필요성을 체크(로그인 요청이 POST 요청인지 확인하여 인증을 진행 할지 여부 판단)
     - POST 요청임이 확인되면 이를 통해 principal을 username, credentials을 password로 담은 Authentication 객체 반환
     - jsonUsernamePasswordLoginFilter()와 관련된 아래에 등장할 메서드 :
           passwordEncoder(), authenticationManager(), loginSuccessJWTProvideHandler(), loginFailureHandler(), jsonUsernamePasswordLoginFilter()
       (더 자세한 과정은 JsonUsernamePasswordAuthenticationFilter.java 내부 주석 참고)
       이후 반환 값을 통해 로그인 인증의 성공여부와 실패여부를 따지며, 성공 시 SecurityContextHolder에 인증정보(Authentication 객체)를 저장하고 핸들러를 통해 성공 메서드 호출
    */

    /*
    * jwtAuthenticationProcessingFilter() 필터란? + 수행할 역할은?
     - 사용자가 로그인 성공 시, 이용하는데 로그인이 필요한 후속 요청들은 JWT 토큰이 포함되어 있으며 해당 토큰을 통해 서비스 이용 및 리소스 접근을 가능하게 하기 위한 필터
     - JWT는 header, payload, signature로 구성
       header에는 토큰의 타입(type)과 해싱한 알고리즘(algorithms)이 무엇인지 표시하며 해당 서비스에서는 JWT에 HS512(HMAC using SHA-512) 알고리즘을 적용할 예정
       payload에는 JWT에 담을 정보를 표시하며, 이 부분의 구현 방식과 사용법은 아직 공부중
       signature은 JWT 토큰의 header와 payload를 BASE64로 인코딩하고, 인코딩 한 값을 비밀키를 이용하여 header에서 정한 알고리즘(HS512)으로 해싱 후, 다시 BASE64로 인코딩하여 생성
           클라이언트 측에서 요청과 함께 JWT 토큰을 보내면, 해당 토큰의 signature가 서버의 header, payload를 위 방식대로 암호화한 결과와 같다면 인증 통과
     - 해당 필터는 Access Token과 Refresh Token을 이용
       Access Token : 사용자가 로그인 성공 시, 서버가 발급하는 토큰. 로그인 이후 후속 요청(인증이 필요한 요청)시 해당 토큰을 헤더에 담아 서버에 전송. 만료 시, 인증 실패 처리
       Refresh Token : 사용자가 로그인 성공 시, 서버는 해당 유저 DB에 Refresh Token을 발급. Access Token이 만료 시, Refresh Token을 이용하여 서버에게 새로운 Access Token 요청
    */

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    /*
    스프링 시큐리티에서 지원해주는 PasswrodEncoder를 Bean을 통해 등록
    * PasswordEncoder 인터페이스 구성요소
     - String encode(charSequence rawPassword); // 비밀번호 단방향 암호화
     - boolean matches(CharSequeunce rawPassword, String encodedPassword); // 암호화X 비밀번호(raw)와 암호화 비밀번호(encoded)가 일치하는지 비교, 복호화 과정 없이 비교(안전성)
     - default boolean upgradeEncoding(String encodedPassword) {return false;}; // 암호화 비밀번호를 다시 암호화 할 경우 진행 여부를 설정
    * createDelegatingPasswordEncoder()란?
     BCrypt를 이용하여 암호화하기 위해 필요한 PasswordEncoder 생성자 DelegatingPasswordEncoder(encodingId, encoders) 리턴
    * DelagatingPasswordEncoder(string, Map<String, passwordEncoder>)란?
     암호화 되지 않은 비밀번호(raw)를 "{bcrypt}BCryptPasswordEncoder로 암호화된 문자열"로 암호화(encoded)해주는 역할 수행
    * 참고사항
     BCrypt는 암호화 할 때 해쉬 알고리즘인 SHA-256을 사용하는 방식을 말하며, 해쉬 알고리즘이기에 복호화가 불가능함
     따라서 DB에 저장할 때, BCrypt를 통해 저장한다면 복호화가 불가능하므로 비밀번호 확인은 할 수 없으며 임시 또는 새로 변경만 가능
     PasswordEncoder 인터페이스의 mathches는 로그인 할 때 입력한 암호화 X 비밀번호(raw)와 DB에 저장된 암호화 비밀번호(encoded)가 일치하는지 비교 기능 제공
     현재 SHA-256 알고리즘(SHA-2로 분류)은 대한민국 인터넷뱅킹, 비트코인의 작업증명에서 사용되고 있으며 이보다 업그레이드 된 SHA-3는 이더리움에서 사용되고 있음
    */

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); // PasswordEncoder를 사용하는 AuthenticationProvider를 DaoAuthenticationProvider로 설정함
        provider.setPasswordEncoder(passwordEncoder()); // PasswordEncoder로는 PasswordEncoderFactories.createDelegatingPasswordEncoder()를 사용하도록 위에서 설정함
        provider.setUserDetailsService(loginService); // 로그인 요청의 성공, 실패 여부를 검증
        return new ProviderManager(provider);
    }

    @Bean
    public LoginSuccessJWTProvideHandler loginSuccessJWTProvideHandler(){
        return new LoginSuccessJWTProvideHandler(jwtService, memberRepository);
    } // jsonUsernamePasswordLoginFilter() 필터를 통한 로그인 인증 성공 시, 수행되는 핸들러

    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    } // jsonUsernamePasswordLoginFilter() 필터를 통한 로그인 인증 실패 시, 수행되는 핸들러

    @Bean
    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordLoginFilter(){
        JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordLoginFilter = new JsonUsernamePasswordAuthenticationFilter(objectMapper);
        jsonUsernamePasswordLoginFilter.setAuthenticationManager(authenticationManager());
        jsonUsernamePasswordLoginFilter.setAuthenticationSuccessHandler(loginSuccessJWTProvideHandler());
        jsonUsernamePasswordLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return jsonUsernamePasswordLoginFilter;
    } // jsonUsernamePasswordLoginFilter()를 사용하기 위한 세팅 설정

    @Bean
    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter(){
        JwtAuthenticationProcessingFilter jsonUsernamePasswordLoginFilter = new JwtAuthenticationProcessingFilter(jwtService, memberRepository);

        return jsonUsernamePasswordLoginFilter;
    }
}