package com.example.YICcapstone.domain.basket.dto;

import com.example.YICcapstone.domain.basket.entity.EbookBasket;
import lombok.Builder;
import lombok.Data;

@Data
public class EbookBasketDto {
    private Long id;
    private String category;
    private String ebookName;
    private String author;
    private String publisher;
    private int price;
    private String imageUrl;
    //private Ebook ebook;
    //private Member member;

    @Builder
    public EbookBasketDto(EbookBasket ebookBasket) {
        this.id = ebookBasket.getEbook().getId();
        this.category = ebookBasket.getEbook().getCategory();
        this.ebookName = ebookBasket.getEbook().getEbookName();
        this.author = ebookBasket.getEbook().getAuthor();
        this.publisher = ebookBasket.getEbook().getPublisher();
        this.price = ebookBasket.getEbook().getPrice();
        this.imageUrl = ebookBasket.getEbook().getImageUrl();
        //this.ebook = ebookBasket.getEbook();
        //this.member = ebookBasket.getMember();
    }
}
