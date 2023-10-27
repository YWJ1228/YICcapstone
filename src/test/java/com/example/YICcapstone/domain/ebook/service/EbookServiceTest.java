package com.example.YICcapstone.domain.ebook.service;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.domain.EbookCategory;
import com.example.YICcapstone.domain.ebook.dto.request.EbookCreationRequest;
import com.example.YICcapstone.domain.ebook.dto.response.EbookResponse;
import com.example.YICcapstone.domain.ebook.repository.EbookCategoryRepository;
import com.example.YICcapstone.domain.ebook.repository.EbookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EbookServiceTest {
    @Autowired
    EbookService ebookService;
    @Autowired
    EbookRepository ebookRepository;
    @Autowired
    EbookCategoryRepository ebookCategoryRepository;

    @BeforeEach
    void before() {
        ebookCategoryRepository.save(
                new EbookCategory("novel")
        );
        ebookCategoryRepository.save(
                new EbookCategory("essay")
        );
        ebookCategoryRepository.save(
                new EbookCategory("poem")
        );
    }

    @Test
    @Transactional
    void Ebook_생성() {
        // 1. 예상 데이터
        EbookCreationRequest ebookCreationRequest = new EbookCreationRequest(
                "title", "author",
                300, "publisher",
                10000, "image",
                "comment", "content", "novel");
        // 2. 실제 데이터
        ebookService.createEbook(ebookCreationRequest);
        // 3. 비교 및 검증
        assertEquals(1, ebookRepository.findAll().size());
        assertEquals("title", ebookRepository.findAll().get(0).getEbookName());
        assertEquals("author", ebookRepository.findAll().get(0).getAuthor());
        assertEquals(300, ebookRepository.findAll().get(0).getPages());
        assertEquals("publisher", ebookRepository.findAll().get(0).getPublisher());
        assertEquals(10000, ebookRepository.findAll().get(0).getPrice());
        assertEquals("image", ebookRepository.findAll().get(0).getImageUrl());
        assertEquals("comment", ebookRepository.findAll().get(0).getComment());
        assertEquals("content", ebookRepository.findAll().get(0).getContent());
        assertEquals("novel", ebookRepository.findAll().get(0).getEbookCategory().getClassification());
    }

    @Test
    @Transactional
    void Ebook_수정() {
        // 1. 예상 데이터
        EbookCreationRequest ebookCreationRequest = new EbookCreationRequest(
                "title", "author",
                300, "publisher",
                10000, "image",
                "comment", "content", "novel");
        EbookCreationRequest ebookCreationRequest2 = new EbookCreationRequest(
                "title2", "author2",
                3002, "publisher2",
                100002, "image2",
                "comment2", "content2", "essay");
        // 2. 실제 데이터
        Ebook createEbook = ebookRepository.save(new Ebook(ebookCreationRequest));
        ebookService.updateEbook(createEbook.getId(), ebookCreationRequest2);
        // 3. 비교 및 검증
        assertEquals(1, ebookRepository.findAll().size());
        assertEquals("title2", ebookRepository.findAll().get(0).getEbookName());
        assertEquals("author2", ebookRepository.findAll().get(0).getAuthor());
        assertEquals(3002, ebookRepository.findAll().get(0).getPages());
        assertEquals("publisher2", ebookRepository.findAll().get(0).getPublisher());
        assertEquals(100002, ebookRepository.findAll().get(0).getPrice());
        assertEquals("image2", ebookRepository.findAll().get(0).getImageUrl());
        assertEquals("comment2", ebookRepository.findAll().get(0).getComment());
        assertEquals("content2", ebookRepository.findAll().get(0).getContent());
        assertEquals("essay", ebookRepository.findAll().get(0).getEbookCategory().getClassification());
    }

    @Test
    @Transactional
    void Ebook_삭제() {
        // 1. 예상 데이터
        EbookCreationRequest ebookCreationRequest = new EbookCreationRequest(
                "title", "author",
                300, "publisher",
                10000, "image",
                "comment", "content", "novel");
        // 2. 실제 데이터
        Ebook createEbook = ebookRepository.save(new Ebook(ebookCreationRequest));
        ebookService.deleteEbook(createEbook.getId());
        // 3. 비교 및 검증
        assertEquals(0, ebookRepository.findAll().size());
    }

    @Test
    @Transactional
    void Ebook_상세페이지() {
        // 1. 예상 데이터
        EbookCreationRequest ebookCreationRequest = new EbookCreationRequest(
                "title", "author",
                300, "publisher",
                10000, "image",
                "comment", "content", "novel");
        // 2. 실제 데이터
        ebookService.createEbook(ebookCreationRequest);
        Ebook createEbook = ebookRepository.findAll().get(0);
        EbookResponse ebookDetail = ebookService.getEbook(createEbook.getId());
        // 3. 비교 및 검증
        assertEquals(1, ebookDetail.getViewCount());
        assertEquals(0, ebookDetail.getPurchaseCount());
        assertEquals(0, ebookDetail.getRating());
        assertEquals("title", ebookDetail.getEbookName());
        assertEquals("author", ebookDetail.getAuthor());
        assertEquals(300, ebookDetail.getPages());
        assertEquals("publisher", ebookDetail.getPublisher());
        assertEquals(10000, ebookDetail.getPrice());
        assertEquals("image", ebookDetail.getImageUrl());
        assertEquals("comment", ebookDetail.getComment());
        assertEquals("content", ebookDetail.getContent());
        assertEquals("novel", ebookDetail.getEbookCategory());
    }

    @Test
    @Transactional
    void Ebook_리스트() {
        // 1. 예상 데이터
        EbookCreationRequest ebookCreationRequest = new EbookCreationRequest(
                "title", "author",
                300, "publisher",
                10000, "image",
                "comment", "content", "novel");
        EbookCreationRequest ebookCreationRequest2 = new EbookCreationRequest(
                "title2", "author2",
                3002, "publisher2",
                100002, "image2",
                "comment2", "content2", "essay");
        EbookCreationRequest ebookCreationRequest3 = new EbookCreationRequest(
                "title3", "author3",
                3003, "publisher3",
                100003, "image3",
                "comment3", "content3", "poem");
        // 2. 실제 데이터
        ebookService.createEbook(ebookCreationRequest);
        ebookService.createEbook(ebookCreationRequest2);
        ebookService.createEbook(ebookCreationRequest3);
        // 3. 비교 및 검증
        assertEquals(3, ebookService.getEbookList(0, 10).getTotalElements());
        assertEquals(3, ebookService.getEbookList(0, 10).getContent().size());
        assertEquals("title3", ebookService.getEbookList(0, 10).getContent().get(0).getEbookName());
        assertEquals("title2", ebookService.getEbookList(0, 10).getContent().get(1).getEbookName());
        assertEquals("title", ebookService.getEbookList(0, 10).getContent().get(2).getEbookName());
    }

    @Test
    @Transactional
    void Ebook_카테고리별리스트() {
        // 1. 예상 데이터
        EbookCreationRequest ebookCreationRequest = new EbookCreationRequest(
                "title", "author",
                300, "publisher",
                10000, "image",
                "comment", "content", "novel");
        EbookCreationRequest ebookCreationRequest2 = new EbookCreationRequest(
                "title2", "author2",
                3002, "publisher2",
                100002, "image2",
                "comment2", "content2", "essay");
        EbookCreationRequest ebookCreationRequest3 = new EbookCreationRequest(
                "title3", "author3",
                3003, "publisher3",
                100003, "image3",
                "comment3", "content3", "poem");
        // 2. 실제 데이터
        ebookService.createEbook(ebookCreationRequest);
        ebookService.createEbook(ebookCreationRequest2);
        ebookService.createEbook(ebookCreationRequest3);
        // 3. 비교 및 검증
        assertEquals(1, ebookService.getEbookListByCategory("novel", 0, 10).getTotalElements());
        assertEquals(1, ebookService.getEbookListByCategory("novel", 0, 10).getContent().size());
        assertEquals("title", ebookService.getEbookListByCategory("novel", 0, 10).getContent().get(0).getEbookName());
        assertEquals(1, ebookService.getEbookListByCategory("essay", 0, 10).getTotalElements());
        assertEquals(1, ebookService.getEbookListByCategory("essay", 0, 10).getContent().size());
        assertEquals("title2", ebookService.getEbookListByCategory("essay", 0, 10).getContent().get(0).getEbookName());
        assertEquals(1, ebookService.getEbookListByCategory("poem", 0, 10).getTotalElements());
        assertEquals(1, ebookService.getEbookListByCategory("poem", 0, 10).getContent().size());
        assertEquals("title3", ebookService.getEbookListByCategory("poem", 0, 10).getContent().get(0).getEbookName());
    }
}