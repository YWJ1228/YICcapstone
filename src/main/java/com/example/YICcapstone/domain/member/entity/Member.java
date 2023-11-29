package com.example.YICcapstone.domain.member.entity;

import com.example.YICcapstone.domain.basket.entity.EbookBasket;
import com.example.YICcapstone.domain.basket.entity.VoiceModelBasket;
import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import com.example.YICcapstone.domain.purchase.domain.VoiceModelPurchase;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModelPreference;
import com.example.YICcapstone.domain.wish.domain.EbookWish;
import com.example.YICcapstone.domain.wish.domain.VoiceModelWish;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

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

    @Column(length = 100, nullable = false)
    private String familyName;

    @Column(length = 100, nullable = false)
    private String givenName;

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


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<VoiceModelPurchase> voiceModelPurchaseList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<EbookPurchase> ebookPurchaseList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<EbookBasket> ebookBasketList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<VoiceModelBasket> voiceModelBasketList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<VoiceModelPreference> voiceModelPreferenceList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<VoiceModelWish> voiceModelWishList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<EbookWish> ebookWishList = new ArrayList<>();

    public void addName(String familyName, String givenName) {this.name = familyName + givenName; }
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
