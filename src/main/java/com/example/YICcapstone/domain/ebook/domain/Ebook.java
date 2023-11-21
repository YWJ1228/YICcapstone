package com.example.YICcapstone.domain.ebook.domain;

import com.example.YICcapstone.domain.ebook.dto.request.EbookCreationRequest;
import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Setter
@Getter
public class Ebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Lob
    private String content;

    @Column
    private String category;
    private String ebookName;
    private String author;
    private int pages;
    private String publisher;
    private int price;
    private String imageUrl;
    private String comment;
    private int viewCount = 0;
    private LocalDateTime uploadedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "ebook")
    private List<EbookPurchase> ebookPurchaseList = new ArrayList<>();


    public Ebook(EbookCreationRequest ebookCreationRequest) {
        this.ebookName = ebookCreationRequest.getEbookName();
        this.category = ebookCreationRequest.getCategory();
        this.author = ebookCreationRequest.getAuthor();
        this.pages = ebookCreationRequest.getPages();
        this.publisher = ebookCreationRequest.getPublisher();
        this.price = ebookCreationRequest.getPrice();
        this.imageUrl = ebookCreationRequest.getImageUrl();
        this.comment = ebookCreationRequest.getComment();
        this.content = ebookCreationRequest.getContent();
    }

    public void update(EbookCreationRequest ebookCreationRequest) {
        this.ebookName = ebookCreationRequest.getEbookName();
        this.category = ebookCreationRequest.getCategory();
        this.author = ebookCreationRequest.getAuthor();
        this.pages = ebookCreationRequest.getPages();
        this.publisher = ebookCreationRequest.getPublisher();
        this.price = ebookCreationRequest.getPrice();
        this.imageUrl = ebookCreationRequest.getImageUrl();
        this.comment = ebookCreationRequest.getComment();
        this.content = ebookCreationRequest.getContent();
    }

}
