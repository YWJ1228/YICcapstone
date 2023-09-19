package com.example.YICcapstone.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // 우준 : Auditing 기능(created_at 포함)을 사용하는 엔티티에 선언
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String nickname;

    @Column (nullable = false)
    private Integer sex;

    @CreatedDate
    @Column(updatable = false) // 우준 : UPDATE 쿼리문에 의한 변경 방지
    private LocalDateTime created_at;

    @Column (nullable = false)
    private Integer role;
}