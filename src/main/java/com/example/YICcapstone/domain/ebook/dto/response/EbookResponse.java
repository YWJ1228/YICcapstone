package com.example.YICcapstone.domain.ebook.dto.response;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EbookResponse {
    private Long id;
    private String category;
    private String ebookName;
    private String author;
    private int pages;
    private String publisher;
    private int price;
    private String imageUrl;
    private String comment;
    private String content;
    private int viewCount;
    private int purchaseCount;
    private double rating;
    private String uploadedAt;

    public EbookResponse(Ebook ebook){
        id = ebook.getId();
        category = ebook.getCategory();
        ebookName = ebook.getEbookName();
        author = ebook.getAuthor();
        pages = ebook.getPages();
        publisher = ebook.getPublisher();
        price = ebook.getPrice();
        imageUrl = ebook.getImageUrl();
        comment = ebook.getComment();
        content = ebook.getContent();
        viewCount = ebook.getViewCount();
        purchaseCount = ebook.getPurchaseCount();
        rating = ebook.getRating();
        uploadedAt = timeFormat(ebook.getUploadedAt());
    }

    public String timeFormat(LocalDateTime time) {
        String year = time.toString().substring(0,4);
        String month = time.toString().substring(5,7);
        String day = time.toString().substring(8,10);
        return year + "년 " + month + "월 " + day + "일";
    }
}
