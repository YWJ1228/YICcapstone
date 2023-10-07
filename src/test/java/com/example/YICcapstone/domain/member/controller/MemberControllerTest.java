package com.example.YICcapstone.domain.member.controller;

import com.example.YICcapstone.domain.member.dto.MemberSignUpDto;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.entity.Sex;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    EntityManager em;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    PasswordEncoder passwordEncoder;

    private static String SIGN_UP_URL = "/api/sign-up";
    private String username = "test@test.com";
    private String password = "test1234@^^";
    private String name = "YIC";
    private String nickname = "YIC";
    private String birth = "990101";
    private Sex sex = Sex.MAN;
    
    private void clear(){
        em.flush();
        em.clear();
    }

    private void signUp(String signUpData) throws Exception {
        mockMvc.perform(
                        post(SIGN_UP_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(signUpData))
                .andExpect(status().isOk());
    }

    @Value("${jwt.access.header}")
    private String accessHeader;

    private static final String BEARER = "Bearer ";

    private String getAccessToken() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);

        MvcResult result = mockMvc.perform(
                        post("/api/log-in")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(map)))
                .andExpect(status().isOk()).andReturn();

        return result.getResponse().getHeader(accessHeader);
    }

    @Test
    public void 회원가입_성공() throws Exception {
        //given
        String signUpData = objectMapper.writeValueAsString(new MemberSignUpDto(username, password, name, nickname, birth, sex));

        //when
        signUp(signUpData);

        //then
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new Exception("회원이 없습니다"));
        assertThat(member.getName()).isEqualTo(name);
        assertThat(memberRepository.findAll().size()).isEqualTo(3); // data.sql 더미데이터 2개 존재
    }

    @Test
    public void 닉네임수정_성공() throws Exception {
        //given
        String signUpData = objectMapper.writeValueAsString(new MemberSignUpDto(username, password, name, nickname, birth, sex));
        signUp(signUpData);

        String accessToken = getAccessToken();
        Map<String, Object> map = new HashMap<>();
        map.put("nickname",nickname+"변경");
        String updateMemberData = objectMapper.writeValueAsString(map);


        //when
        mockMvc.perform(
                        patch("/api/user/nickname")
                                .header(accessHeader,BEARER+accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateMemberData))
                .andExpect(status().isOk());

        //then
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new Exception("회원이 없습니다"));
        assertThat(member.getNickname()).isEqualTo(nickname+"변경");
        assertThat(memberRepository.findAll().size()).isEqualTo(3); // data.sql 더미데이터 2개 존재

    }

    @Test
    public void 비밀번호수정_성공() throws Exception {
        //given
        String signUpData = objectMapper.writeValueAsString(new MemberSignUpDto(username, password, name, nickname, birth, sex));
        signUp(signUpData);

        String accessToken = getAccessToken();

        Map<String, Object> map = new HashMap<>();
        map.put("checkPassword",password);
        map.put("changePassword",password+"!@#@");

        String updatePassword = objectMapper.writeValueAsString(map);

        //when
        mockMvc.perform(
                        patch("/api/user/password")
                                .header(accessHeader,BEARER+accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updatePassword))
                .andExpect(status().isOk());

        //then
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new Exception("회원이 없습니다"));
        assertThat(passwordEncoder.matches(password, member.getPassword())).isFalse();
        assertThat(passwordEncoder.matches(password+"!@#@", member.getPassword())).isTrue();
    }

    @Test
    public void 비밀번호수정_실패_검증비밀번호가_틀림() throws Exception {
        //given
        String signUpData = objectMapper.writeValueAsString(new MemberSignUpDto(username, password, name, nickname, birth, sex));
        signUp(signUpData);

        String accessToken = getAccessToken();

        Map<String, Object> map = new HashMap<>();
        map.put("checkPassword",password+"123");
        map.put("changePassword",password+"!@#@");

        String updatePassword = objectMapper.writeValueAsString(map);

        //when
        mockMvc.perform(
                        patch("/api/user/password")
                                .header(accessHeader,BEARER+accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updatePassword))
                .andExpect(status().isBadRequest());

        //then
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new Exception("회원이 없습니다"));
        assertThat(passwordEncoder.matches(password, member.getPassword())).isTrue();
        assertThat(passwordEncoder.matches(password+"!@#@", member.getPassword())).isFalse();
    }

    @Test
    public void 회원탈퇴_성공() throws Exception {
        //given
        String signUpData = objectMapper.writeValueAsString(new MemberSignUpDto(username, password, name, nickname, birth, sex));
        signUp(signUpData);

        String accessToken = getAccessToken();

        Map<String, Object> map = new HashMap<>();
        map.put("checkPassword",password);

        String updatePassword = objectMapper.writeValueAsString(map);

        //when
        mockMvc.perform(
                        delete("/api/user")
                                .header(accessHeader,BEARER+accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updatePassword))
                .andExpect(status().isOk());

        //then
        assertThrows(Exception.class, () -> memberRepository.findByUsername(username).orElseThrow(() -> new Exception("회원이 없습니다")));
    }

    @Test
    public void 회원탈퇴_실패_비밀번호틀림() throws Exception {
        //given
        String signUpData = objectMapper.writeValueAsString(new MemberSignUpDto(username, password, name, nickname, birth, sex));
        signUp(signUpData);

        String accessToken = getAccessToken();

        Map<String, Object> map = new HashMap<>();
        map.put("checkPassword",password+11);

        String updatePassword = objectMapper.writeValueAsString(map);

        //when
        mockMvc.perform(
                        delete("/api/user")
                                .header(accessHeader,BEARER+accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updatePassword))
                .andExpect(status().isBadRequest());

        //then
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new Exception("회원이 없습니다"));
        assertThat(member).isNotNull();
    }

    @Test
    public void 회원탈퇴_실패_권한이없음() throws Exception {
        //given
        String signUpData = objectMapper.writeValueAsString(new MemberSignUpDto(username, password, name, nickname, birth, sex));
        signUp(signUpData);

        String accessToken = getAccessToken();

        Map<String, Object> map = new HashMap<>();
        map.put("checkPassword",password);

        String updatePassword = objectMapper.writeValueAsString(map);

        //when
        mockMvc.perform(
                        delete("/api/user")
                                .header(accessHeader,BEARER+accessToken+"1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updatePassword))
                .andExpect(status().isForbidden());

        //then
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new Exception("회원이 없습니다"));
        assertThat(member).isNotNull();
    }
}