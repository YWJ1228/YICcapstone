package com.example.YICcapstone.domain.ebook.service;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.domain.EbookCategory;
import com.example.YICcapstone.domain.ebook.dto.request.EbookCreationRequest;
import com.example.YICcapstone.domain.ebook.exception.EbookCategoryNotFoundException;
import com.example.YICcapstone.domain.ebook.exception.EbookNotFoundException;
import com.example.YICcapstone.domain.ebook.repository.EbookCategoryRepository;
import com.example.YICcapstone.domain.ebook.repository.EbookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class EbookService {
    @Autowired
    private EbookRepository ebookRepository;
    @Autowired
    private EbookCategoryRepository ebookCategoryRepository;


    @Transactional
    public void createEbook(EbookCreationRequest ebookCreationRequest) {
        Ebook ebook = new Ebook(ebookCreationRequest);
        EbookCategory ebookCategory = ebookCategoryRepository.findByClassification(ebookCreationRequest.getClassification())
                .orElseThrow(EbookCategoryNotFoundException::new);
        log.info(ebookCategory.getId().toString());
        ebook.setEbookCategory(ebookCategory);
        ebookRepository.save(ebook);
    }

    @Transactional
    public void updateEbook(Long ebookId, EbookCreationRequest ebookCreationRequest) {
        Ebook ebook = ebookRepository.findById(ebookId)
                .orElseThrow(EbookNotFoundException::new);
        EbookCategory ebookCategory = ebookCategoryRepository.findByClassification(ebookCreationRequest.getClassification())
                .orElseThrow(EbookCategoryNotFoundException::new);
        ebook.setEbookCategory(ebookCategory);
        ebook.update(ebookCreationRequest);
    }

    @Transactional
    public void deleteEbook(Long ebookId) {
        Ebook ebook = ebookRepository.findById(ebookId)
                .orElseThrow(EbookNotFoundException::new);
        ebookRepository.delete(ebook);
    }

    @Transactional
    public Ebook getEbook(Long ebookId) {
        Ebook ebook = ebookRepository.findById(ebookId)
                .orElseThrow(EbookNotFoundException::new);

        ebook.setViewCount(ebook.getViewCount() + 1);

        return ebook;
    }

    @Transactional(readOnly = true)
    public Page<Ebook> getEbookList(int page) {
        return ebookRepository.findAllByOrderByUploadedAtDesc(PageRequest.of(page, 10));
    }

    @Transactional(readOnly = true)
    public Page<Ebook> getEbookListByCategory(String classification, int page) {
        return ebookRepository.findAllByEbookCategory_ClassificationOrderByUploadedAtDesc(classification, PageRequest.of(page, 10));
    }

    @Transactional(readOnly = true)
    public Page<Ebook> getEbookListByPopularity(int page){
        return ebookRepository.findAllByOrderByScoreDesc(PageRequest.of(page, 10));
    }
}