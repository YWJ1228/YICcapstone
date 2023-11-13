package com.example.YICcapstone.domain.basket.entity;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class VoiceModelBasket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne // VoiceModelBasket:many, VoiceModel:one (외래키)
    @JoinColumn
    private VoiceModel voiceModel;

    @ManyToOne // VoiceModelBasket:many, Member:one (외래키)
    @JoinColumn
    private Member member;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
