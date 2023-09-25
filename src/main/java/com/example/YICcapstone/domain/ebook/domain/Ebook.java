package com.example.YICcapstone.domain.ebook.domain;

import com.example.YICcapstone.domain.ebook.dto.request.EbookCreationRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Setter
@Getter
public class Ebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ebook_category_id")
    private EbookCategory ebookCategory;
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


    public Ebook(EbookCreationRequest ebookCreationRequest) {
        this.ebookName = ebookCreationRequest.getEbookName();
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
        this.author = ebookCreationRequest.getAuthor();
        this.pages = ebookCreationRequest.getPages();
        this.publisher = ebookCreationRequest.getPublisher();
        this.price = ebookCreationRequest.getPrice();
        this.imageUrl = ebookCreationRequest.getImageUrl();
        this.comment = ebookCreationRequest.getComment();
        this.content = ebookCreationRequest.getContent();
    }

    public int getScore(){
        return viewCount + (purchaseCount * 10);
    }

}
