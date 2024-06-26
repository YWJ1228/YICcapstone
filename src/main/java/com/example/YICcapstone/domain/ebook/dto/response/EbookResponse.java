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
    private Integer purchaseCount;
    private String rating;
    private String uploadedAt;

    public EbookResponse(Ebook ebook) {
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
        purchaseCount = ebook.getEbookPurchaseList().size();
        rating = ratingFormat(ebook.getEbookPurchaseList().stream().filter(ebookPurchase -> ebookPurchase.getGrade() != null)
                .mapToDouble(ebookPurchase -> ebookPurchase.getGrade()).average().orElse(0));
        uploadedAt = timeFormat(ebook.getUploadedAt());
    }

    public String timeFormat(LocalDateTime time) {
        String year = time.toString().substring(0, 4);
        String month = time.toString().substring(5, 7);
        String day = time.toString().substring(8, 10);
        return year + "년 " + month + "월 " + day + "일";
    }

    public String ratingFormat(double rating) {
        if (rating == 0) {
            return "아직 평점이 없어요.";
        }
        return String.format("%.1f", rating);
    }
}
