package com.example.YICcapstone.global.login.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class JsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    // 로그인 인증과 관련한 AbstractAuthenticationProcessingFilter에서 formLogin이 아닌, josn 데이터를 통한 로그인 방식을 사용할 것이므로 추가적인 커스텀 필요
    // 해당 필터의 성공여부와 실패여부는 LoginSuccessJWTProvideHandler와 LoginFailureHandler로 별도로 구현해둠

    private static final String DEFAULT_LOGIN_REQUEST_URL = "/api/log-in"; // "/api/log-in"을 로그인 인증 요청으로 인식하도록 함
    private static final String HTTP_METHOD = "POST"; // POST 요청을 통해서만 로그인 인증을 진행할 수 있도록 설정 (GET 방식 X)
    private static final String CONTENT_TYPE = "application/json"; //formLogin이 아닌, json 데이터를 통한 로그인을 진행하도록 설정
    private final ObjectMapper objectMapper;
    private static final String USERNAME_KEY="username"; // 로그인 요청에 담긴 아이디 정보 (email)
    private static final String PASSWORD_KEY="password"; // 로그인 요청에 담긴 비밀번호 정보 (password)

    private static final AntPathRequestMatcher DEFAULT_LOGIN_PATH_REQUEST_MATCHER =
            new AntPathRequestMatcher(DEFAULT_LOGIN_REQUEST_URL, HTTP_METHOD);

    public JsonUsernamePasswordAuthenticationFilter(ObjectMapper objectMapper) {

        super(DEFAULT_LOGIN_PATH_REQUEST_MATCHER);   // POST 형태의 "/api/log-in" 요청이 아닐 때를 위해 처리(=GET인 경우)

        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(request.getContentType() == null || !request.getContentType().equals(CONTENT_TYPE)  ) {
            throw new AuthenticationServiceException("Authentication Content-Type not supported: " + request.getContentType());
        } // 로그인 요칭이 json 데이터 형식인지 확인

        String messageBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);

        Map<String, String> usernamePasswordMap = objectMapper.readValue(messageBody, Map.class);
        String username = usernamePasswordMap.get(USERNAME_KEY);
        String password = usernamePasswordMap.get(PASSWORD_KEY);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password); //principal과 credentials

        return this.getAuthenticationManager().authenticate(authRequest); //principal과 credentials을 포함한 Authentication 객체 반환
    }
}
