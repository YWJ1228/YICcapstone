package com.example.YICcapstone.domain.ebook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class EbookCreationRequest {
    @NotBlank(message = "Ebook 이름을 입력해주세요.")
    @Size(min = 1, max = 100)
    private String ebookName;
    @NotBlank(message = "Ebook 작가를 입력해주세요.")
    @Size(min = 1, max = 100)
    private String author;
    @NotBlank(message = "Ebook 페이지를 입력해주세요.")
    private int pages;
    @NotBlank(message = "Ebook 출판사를 입력해주세요.")
    @Size(min = 1, max = 100)
    private String publisher;
    @NotBlank(message = "Ebook 가격을 입력해주세요.")
    private int price;
    private String imageUrl;
    @NotBlank(message = "Ebook 소개를 입력해주세요.")
    @Size(min = 2)
    private String comment;
    @NotBlank(message = "Ebook 내용을 입력해주세요.")
    @Size(min = 2)
    private String content;
    @NotBlank(message = "Ebook 카테고리를 선택해주세요.")
    private String category;

}
