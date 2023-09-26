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
    // 인증과 관련한 AbstractAuthenticationProcessingFilter에서 formLogin이 아닌, josn 데이터를 통한 인증 로직이므로 설정 필요
    // 해당 필터의 성공 처리와 실패 처리를 제공할 Handler는 global 내 동일 경로에 생성해 둠.

    private static final String DEFAULT_LOGIN_REQUEST_URL = "/api/log-in";  // /api/member/log-in/oauth2/ + ????? 로 오는 요청을 처리

    private static final String HTTP_METHOD = "POST";    //HTTP 메서드의 방식은 POST로 지정 (이 경우 GET으로 로그인 인증 X)

    private static final String CONTENT_TYPE = "application/json";//json 타입의 데이터로만 로그인을 진행

    private final ObjectMapper objectMapper;

    private static final String USERNAME_KEY="username"; // username = email
    private static final String PASSWORD_KEY="password";


    private static final AntPathRequestMatcher DEFAULT_LOGIN_PATH_REQUEST_MATCHER =
            new AntPathRequestMatcher(DEFAULT_LOGIN_REQUEST_URL, HTTP_METHOD); //=>   login 의 요청에, POST로 온 요청에 매칭된다.

    public JsonUsernamePasswordAuthenticationFilter(ObjectMapper objectMapper) {

        super(DEFAULT_LOGIN_PATH_REQUEST_MATCHER);   // 위에서 설정한  /oauth2//api/member/log-in* 의 요청에, GET으로 온 요청을 처리하기 위해 설정한다.

        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(request.getContentType() == null || !request.getContentType().equals(CONTENT_TYPE)  ) {
            throw new AuthenticationServiceException("Authentication Content-Type not supported: " + request.getContentType());
        }

        String messageBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);

        Map<String, String> usernamePasswordMap = objectMapper.readValue(messageBody, Map.class);

        String username = usernamePasswordMap.get(USERNAME_KEY);
        String password = usernamePasswordMap.get(PASSWORD_KEY);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);//principal 과 credentials 전달

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}