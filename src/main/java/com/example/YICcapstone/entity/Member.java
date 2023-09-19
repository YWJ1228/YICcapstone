package com.example.YICcapstone.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 255, nullable = false,unique = true)
    private String email; // 우준 : 아이디는 중복 될 수 없게 설정 unique = true

    private String password;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private Integer sex;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 우준 : 정보 수정(비밀번호, 닉네임만)
    public void updatePassword(PasswordEncoder passwordEncoder, String password){
        this.password = passwordEncoder.encode(password);
    }
    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

    // 우준 : 비밀번호는 암호화 후 데이터베이스에 저장
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }
}