package com.example.YICcapstone.domain.wish.domain;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EbookWish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ebook_id")
    private Ebook ebook;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    public EbookWish(Member member, Ebook ebook){
        this.member = member;
        this.ebook = ebook;
    }
}
