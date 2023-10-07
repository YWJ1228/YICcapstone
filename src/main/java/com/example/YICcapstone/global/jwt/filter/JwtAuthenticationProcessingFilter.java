package com.example.YICcapstone.global.jwt.filter;

import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.global.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
    private final String NO_CHECK_URL = "/api/log-in";
    /*
        Refresh Token이 요청으로 온 경우 -> 유효하면 Access Token 재 발급 후, 필터 진행 X(인증된 요청으로 간주하기 때문)
        Refresh Token이 없고 AccessToken만 있는 경우 -> 인증이 필요한 요청이므로 유저 정보 저장 후에 필터를 계속 진행하게 구현
    */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().equals(NO_CHECK_URL)) {
            filterChain.doFilter(request, response);
            return; // 로그인 요청의 URL인 경우, 토큰을 발급하는 로그인 필터를 진행하도록 넘김
        }
        // 이후 코드는 로그인 중, 클라이언트가 토큰을 통해 어떠한 요청을 한 경우 동작

        String refreshToken = jwtService
                .extractRefreshToken(request)
                .filter(jwtService::isTokenValid)
                .orElse(null);
        // RefreshToken이 만료되었으면 null

        if(refreshToken != null){ // 유효한 RefreshToken이 요청으로 온 것이라면, 관련 유저를 찾아오고 존재하면 Access Token 재발급
            checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
            return; // 해당 경우 재발급이기에 인증이 되었다고 가정하고 인증처리 없이 리턴
        }

        checkAccessTokenAndAuthentication(request, response, filterChain);
        // Refresh Token이 없다면, Access Token 재발급 요청이 아닌, Access Token을 통한 인증 요청이므로 요청으로 온 Access Token을 검사하는 로직 수행
        // request에서 요청으로 Access Token을 추출을 시도하고 Access Token이 존재하면 username 정보를 추출. 추출 성공 시, 해당 회원을 DB에서 찾고 인증처리
        // 인증처리의 반환 값은 스프링 시큐리티에서 제공해주는 SecurityContextHoler의 Authentication 객체(=NullAuthoritiesMapper)
    }

    private void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        jwtService.extractAccessToken(request).filter(jwtService::isTokenValid).ifPresent(

                accessToken -> jwtService.extractUsername(accessToken).ifPresent(

                        username -> memberRepository.findByUsername(username).ifPresent(

                                member -> saveAuthentication(member)
                        )
                )
        );
        filterChain.doFilter(request,response);
    }

    private void saveAuthentication(Member member) {
        UserDetails user = User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authoritiesMapper.mapAuthorities(user.getAuthorities()));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
        memberRepository.findByRefreshToken(refreshToken).ifPresent(
                member -> jwtService.sendAccessToken(response, jwtService.createAccessToken(member.getUsername()))
        );
    }
}
