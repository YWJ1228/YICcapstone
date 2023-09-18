package com.example.YICcapstone.domain.ebook.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Ebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ebookName;
    private String author;
    private int pages;
    private String publisher;
    private int price;
    private String imageUrl;
    private String comment;
    private String content;
    private int viewCount = 0;
    private int purchaseCount = 0;
    private int rating = 0;
    private LocalDateTime uploadedAt = LocalDateTime.now();

    public Ebook(String ebookName, String author, int pages, String publisher, int price, String imageUrl, String comment, String content) {
        this.ebookName = ebookName;
        this.author = author;
        this.pages = pages;
        this.publisher = publisher;
        this.price = price;
        this.imageUrl = imageUrl;
        this.comment = comment;
        this.content = content;
    }

}
