package com.example.YICcapstone.domain.basket.entity;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EbookBasket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne // EbookBasket:many, Ebook:one (외래키)
    @JoinColumn
    private Ebook ebook;

    @ManyToOne // EbookBasket:many, Member:one (외래키)
    @JoinColumn
    private Member member;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
