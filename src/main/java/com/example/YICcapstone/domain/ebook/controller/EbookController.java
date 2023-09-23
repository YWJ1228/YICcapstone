package com.example.YICcapstone.domain.ebook.controller;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.domain.EbookCategory;
import com.example.YICcapstone.domain.ebook.dto.request.EbookCreationRequest;
import com.example.YICcapstone.domain.ebook.repository.EbookCategoryRepository;
import com.example.YICcapstone.domain.ebook.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Autowired
    private EbookService ebookService;
    @Autowired
    private EbookCategoryRepository ebookCategoryRepository;

    @GetMapping("/{ebookId}")
    public Ebook getEbook(@PathVariable Long ebookId) {
        return ebookService.getEbook(ebookId);
    }

    @GetMapping("/list")
    public Page<Ebook> getEbookList(@RequestParam int page) {
        return ebookService.getEbookList(page);
    }

    @GetMapping("/list/category")
    public Page<Ebook> getEbookListByCategory(@RequestParam String classification, @RequestParam int page) {
        return ebookService.getEbookListByCategory(classification, page);
    }

    @PostMapping
    public ResponseEntity<String> createEbook(@RequestBody @Valid EbookCreationRequest ebookCreationRequest) {
        ebookService.createEbook(ebookCreationRequest);
        return ResponseEntity.status(200).body("Ebook created successfully");
    }

    @PutMapping("/{ebookId}")
    public ResponseEntity<String> updateEbook(@PathVariable Long ebookId, @RequestBody @Valid EbookCreationRequest ebookCreationRequest) {
        ebookService.updateEbook(ebookId, ebookCreationRequest);
        return ResponseEntity.status(200).body("Ebook updated successfully");
    }

    @DeleteMapping("/{ebookId}")
    public ResponseEntity<String> deleteEbook(@PathVariable Long ebookId) {
        ebookService.deleteEbook(ebookId);
        return ResponseEntity.status(200).body("Ebook deleted successfully");
    }
}
