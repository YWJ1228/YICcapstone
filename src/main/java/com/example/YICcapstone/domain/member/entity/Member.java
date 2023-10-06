package com.example.YICcapstone.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 255, nullable = false, unique = true)
    private String username;

    private String password;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 255, nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 1000)
    private String refreshToken;

    public void addRole() { this.role = Role.USER; } // 회원 가입 시, 자동으로 USER 등급의 권한 부여하도록 설정
    public void encodePassword(PasswordEncoder passwordEncoder){ // 회원 가입 시, 비밀번호는 DB에 암호화하여 저장
        this.password = passwordEncoder.encode(password);
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
    public void destroyRefreshToken(){
        this.refreshToken = null;
    }

    public void updateNickname(String nickname) { this.nickname = nickname; } // 닉네임 변경
    public void updatePassword(PasswordEncoder passwordEncoder, String password){ // 비밀번호 변경
        this.password = passwordEncoder.encode(password);
    }
    public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword){ // 비밀번호 변경 시, 현재 비밀번호 정상 입력 확인
        return passwordEncoder.matches(checkPassword, getPassword());
    }
}
