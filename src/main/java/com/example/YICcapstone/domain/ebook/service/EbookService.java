package com.example.YICcapstone.domain.ebook.service;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.dto.request.EbookCreationRequest;
import com.example.YICcapstone.domain.ebook.dto.response.EbookResponse;
import com.example.YICcapstone.domain.ebook.exception.EbookNotFoundException;
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


    @Transactional
    public void createEbook(EbookCreationRequest ebookCreationRequest) {
        Ebook ebook = new Ebook(ebookCreationRequest);
        ebookRepository.save(ebook);
    }

    @Transactional
    public void updateEbook(Long ebookId, EbookCreationRequest ebookCreationRequest) {
        Ebook ebook = ebookRepository.findById(ebookId)
                .orElseThrow(EbookNotFoundException::new);
        ebook.update(ebookCreationRequest);
    }

    @Transactional
    public void deleteEbook(Long ebookId) {
        Ebook ebook = ebookRepository.findById(ebookId)
                .orElseThrow(EbookNotFoundException::new);
        ebookRepository.delete(ebook);
    }

    @Transactional
    public EbookResponse getEbook(Long ebookId) {
        Ebook ebook = ebookRepository.findById(ebookId)
                .orElseThrow(EbookNotFoundException::new);

        ebook.setViewCount(ebook.getViewCount() + 1);

        return new EbookResponse(ebook);
    }

    @Transactional(readOnly = true)
    public Page<EbookResponse> getEbookList(int page, int size) {
        return ebookRepository.findAllByOrderByUploadedAtDesc(PageRequest.of(page, size)).map(EbookResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<EbookResponse> getEbookListByCategory(String category, int page, int size) {
        return ebookRepository.findAllByCategoryOrderByUploadedAtDesc(category, PageRequest.of(page, size)).map(EbookResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<EbookResponse> getEbookListByCategorySortedByScore(String category, int page, int size) {
        return ebookRepository.findAllByCategoryOrderByScoreAndUploadedAtDesc(category, PageRequest.of(page, size)).map(EbookResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<EbookResponse> getEbookListByCategorySortedByPriceDesc(String category, int page, int size) {
        return ebookRepository.findAllByCategoryOrderByPriceDescUploadedAtDesc(category, PageRequest.of(page, size)).map(EbookResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<EbookResponse> getEbookListByCategorySortedByPriceAsc(String category, int page, int size) {
        return ebookRepository.findAllByCategoryOrderByPriceAscUploadedAtDesc(category, PageRequest.of(page, size)).map(EbookResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<EbookResponse> getEbookListByPopularity(int page, int size) {
        return ebookRepository.findAllByOrderByScore(PageRequest.of(page, size)).map(EbookResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<EbookResponse> getEbookListSortedByPriceDesc(int page, int size) {
        return ebookRepository.findAllByOrderByPriceDescUploadedAtDesc(PageRequest.of(page, size)).map(EbookResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<EbookResponse> getEbookListSortedByPriceAsc(int page, int size) {
        return ebookRepository.findAllByOrderByPriceAscUploadedAtDesc(PageRequest.of(page, size)).map(EbookResponse::new);
    }

}