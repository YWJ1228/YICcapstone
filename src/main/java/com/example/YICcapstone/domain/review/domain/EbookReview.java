package com.example.YICcapstone.domain.review.domain;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EbookReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ebook_purchase_id")
    private EbookPurchase ebookPurchase;

    @ManyToOne
    @JoinColumn(name = "ebook_id")
    private Ebook ebook;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private String content;
    private int grade;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = null;
    private Boolean isDeleted = false;

}
