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
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginService loginService;
    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    @Bean
    @ConditionalOnProperty(name = "spring.h2.console.enabled",havingValue = "true")
    public WebSecurityCustomizer configureH2ConsoleEnable() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console());
    } // h2-console을 스프링 시큐리티 필터가 차단하지 않도록 설정

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() // 외부 POST 요청(리액트)을 받아야하기 때문에 disable
                .httpBasic().disable() // http 기반 인증이 아닌, JWT 토큰 인증을 사용하기에 disable
                .cors().configurationSource(corsConfigurationSource()); // 리액트와 통신하기 때문에 포트번호가 다르므로 설정 필요

        httpSecurity
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션 사용 X, JWT 사용

        httpSecurity
                .securityMatcher("/api/**") // "/api/" 로 시작하는 모든 URL 및 요청은 시큐리티 적용. /api로 시작하지 않는 URL(=프론트 창)은 차단 X
                .authorizeHttpRequests(request -> request
                        //.requestMatchers("/api/user/**").hasRole("USER") // /api/user/ 로 시작하는 모든 URL은 "USER" 권한만 요청 가능
                        //.requestMatchers("/api/admin/**").hasRole("ADMIN") // /api/admin/ 로 시작하는 모든 URL은 "ADMIN" 권한만 요청 가능
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // 해당 요청은 ADMIN 권한만 요청이 가능하며 나머지는 거부
                        // UDATE member SET role = 'ADMIN' WHERE id = member's primary key 로 바꾸어주어야 "/api/admin/**" 요청이 승인
                        .requestMatchers(HttpMethod.POST, "/api/sign-up/**").permitAll() // 회원가입 요청 시큐리티 차단 X
                        .requestMatchers(HttpMethod.GET, "/api/sign-up/**").permitAll() // 회원가입 요청(닉네임, 이메일 관련) 시큐리티 차단 X
                        .requestMatchers(HttpMethod.POST, "/api/find/**").permitAll() // 아이디, 비밀번호 찾기 시큐리티 차단 X
                        .requestMatchers(HttpMethod.PATCH, "/api/find/**").permitAll() // 비밀번호 찾기(변경) 시큐리티 차단 X
                        //.requestMatchers("~").permitAll() // 모든 사용자가 시큐리티를 사용하지 않아도 허용하는 부분
                        .anyRequest().authenticated() // 그 외 "/api"로 시작하는 다른 URL 및 요청은 시큐리티로 인한 차단, 즉 검증 필요
                ) // 위 예외 설정을 제외한 나머지 요청에 대해서는 아래 필터 설정을 따름
                .addFilterAfter(jsonUsernamePasswordLoginFilter(), LogoutFilter.class) // 로그아웃 상태일 때만 로그인 필터가 동작
                .addFilterBefore(jwtAuthenticationProcessingFilter(), JsonUsernamePasswordAuthenticationFilter.class); // 로그인 필터 전에 JWT 인증 필터 먼저 동작
                // 해당 필터에 관한 자세한 설명은 아래 선언부 참고
                // 간략 설명 - JsonUsernamePassword~ : 로그인 요청으로 온 아이디,비밀번호와 DB에 저장된 암호화 된 회원 정보의 일치 여부를 판단하여 로그인을 진행하는 필터
                //         - jwtAuthentication~ : 로그인 인증이 이루어지기 전, JWT 토큰인 access token과 refresh token을 로그인 인증과 함께 부여하고 앞으로의 통신을 위해 먼저 동작하는 필터
        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    } // 리액트와 통신하기 때문에 포트번호가 다르므로 설정 필요

    @Bean
    public PasswordEncoder passwordEncoder(){ // JsonUsernamePasswordAuthenticationFilter에 필요한 요소 1
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    /*
    Spring Security에서 지원해주는 PasswordEncoder를 Bean을 통해 가져옴
    BCryptPasswordEncoder를 사용하는 DelegatingPasswordEncoder를 사용
    BCrypt 방식은 암호화 할 때 SHA-256알고리즘을 사용하며, 이는 해시 알고리즘이기에 복호화가 불가능하고 일치여부 비교만 가능함
    암호화된 비밀번호는 "{brypt}암호화 된 문자열" 형태가 됨
    현재 SHA-256 알고리즘(SHA-2로 분류)은 대한민국 인터넷뱅킹, 비트코인의 작업증명에서 사용되고 있으며 이보다 업그레이드 된 SHA-3는 이더리움에서 사용되고 있음
    */
    @Bean
    public AuthenticationManager authenticationManager() { // JsonUsernamePasswordAuthenticationFilter에 필요한 요소 2
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(loginService);
        return new ProviderManager(provider);
    }
    @Bean
    public LoginSuccessJWTProvideHandler loginSuccessJWTProvideHandler(){ // JsonUsernamePasswordAuthenticationFilter에 필요한 요소 3
        return new LoginSuccessJWTProvideHandler(jwtService, memberRepository);
    }
    @Bean
    public LoginFailureHandler loginFailureHandler(){ // JsonUsernamePasswordAuthenticationFilter에 필요한 요소 4
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
    /* JsonUsernamePasswordAuthenticationFilter(= jsonUsernamePasswordLoginFilter)란?
        AbstractAuthenticationProcessingFilter에서 상속받은 클래스로서, 특정 요청(해당 경우는 로그인으로 커스텀)이 감지되면 발동하는 필터
        username과 password를 통해 UsernamePasswordAuthenticationToken을 생성하고, 해당 토큰을 AuthenticationManager의 authenticate()인자로 넘긴 후,
        반환 결과인 Authentication 객체(username,UsernamePasswordAuthenticationToken,Userdetails 정보 포함)를 결과로 반환하는 역할을 수행
        반환 할 때, 인증 성공 객체라면 SecurityContext(이를 통해 컨트롤러에서 사용자를 수정하거나 보안정책, 권한 등을 설정 가능)에 이를 담고 핸들러 처리
        로그인 이후, JWT 토큰을 사용하기 때문에 해당 핸들러는 JWT 토큰을 발급하는 기능이 들어감(= LoginSuccesJWTProvideHandler)
        위의 과정을 위해 JsonUsernamePasswordAuthenticationFilter에
        PasswordEncoder + AuthenticationManager + LoginSuccessJWTProvideHandler + LoginFailureHandler를 등록한 것
    */
    @Bean
    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter(){
        JwtAuthenticationProcessingFilter jsonUsernamePasswordLoginFilter = new JwtAuthenticationProcessingFilter(jwtService, memberRepository);

        return jsonUsernamePasswordLoginFilter;
    }
    /*
    jwtAuthenticationProcessingFilter() 필터란?
        JWT : Json 포맷을 이용하여 사용자에 대한 속성을 저장하는 Claim 기반의 Web Token, 1) 로그인 이후 후속 요청에 대한 접근 권한과 2) 서명을 통한 안전한 정보 전달에 사용
            header / payload / signature 로 구성
        header - 토큰의 타입(type)과 사용한 알고리즘(alg)을 표기 / HS512(HMAC using SHA-512) 알고리즘 사용
        payload - name,value의 한쌍으로 이루어진 클레임이라는 정보를 담음 / 서비스에 필요한 인증정보나 토큰에 대한 상세 정보 기록
        signature - 토큰을 인코딩하거나 유효성 검증을 할 때 사용되는 코드가 존재 / 이를 이용하여 서버는 클라이언트가 보낸 토큰이 정상인지 판단
        클라이언트는 보호된 경로 또는 리소스에 접근을 서버에게 요청할 때마다 Authorization 헤더에 JWT를 포함시켜야 함
        서버는 이를 받아 JWT의 signature을 통해 유효성을 검증 후, 클라이언트에게 액세스를 보장

     - 해당 필터는 Access Token과 Refresh Token을 이용
       Access Token과 Refresh Token은 로그인 시, 발급되는 JWT 토큰
       Access Token만 사용하면 Access Token을 3자에게 탈취당했을 경우 보안에 취약하기 때문에 특정 시간이 지나면 Access Token을 만료시켜 재발급 하도록 구현
       이를 위해 Refresh Token을 사용하며, Refresh Token만 서버측의 DB에 저장하고, Access Token보다 유효 시간을 길게 설정
        클라이언트로부터 인증이 필요한 요청이 서버로 들어왔을 때
       1) 둘다 유효한 경우 -> 정상 인증 처리
       2) Access Token만 만료 -> AccessToken 재발급
       3) Refresh Token만 만료 -> AccessToken 검증 후 인증, Refresh Token 재발급 X(원래 재발급이 일반적인 방법이나, 보안 위험 때문에 발급 X 설계)
       4) 모두 만료 -> 로그아웃 시키고, 로그인 화면으로 이동시켜 다시 로그인(재발급) 유도
    */
}