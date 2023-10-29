package com.example.YICcapstone.domain.ebook.controller;

import com.example.YICcapstone.domain.ebook.dto.request.EbookCreationRequest;
import com.example.YICcapstone.domain.ebook.dto.response.EbookResponse;
import com.example.YICcapstone.domain.ebook.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ebook")
public class EbookController {
    @Autowired
    private EbookService ebookService;

    @GetMapping("/{ebookId}")
    public EbookResponse getEbook(@PathVariable Long ebookId) {
        return ebookService.getEbook(ebookId);
    }

    @GetMapping("/list")
    public Page<EbookResponse> getEbookList(@RequestParam int page, @RequestParam int size) {
        return ebookService.getEbookList(page, size);
    }

    @GetMapping("/list/category")
    public Page<EbookResponse> getEbookListByCategory(@RequestParam String category, @RequestParam int page, @RequestParam int size) {
        return ebookService.getEbookListByCategory(category, page, size);
    }

    @GetMapping("/list/category/popularity")
    public Page<EbookResponse> getEbookListByScoreAndUploadedAtDesc(@RequestParam String category, @RequestParam int page, @RequestParam int size) {
        return ebookService.getEbookListByCategorySortedByScore(category, page, size);
    }

    @GetMapping("/list/category/price/desc")
    public Page<EbookResponse> getEbookListByPriceDesc(@RequestParam String category, @RequestParam int page, @RequestParam int size) {
        return ebookService.getEbookListByCategorySortedByPriceDesc(category, page, size);
    }

    @GetMapping("/list/category/price/asc")
    public Page<EbookResponse> getEbookListByPriceAsc(@RequestParam String category, @RequestParam int page, @RequestParam int size) {
        return ebookService.getEbookListByCategorySortedByPriceAsc(category, page, size);
    }

    @GetMapping("/list/popularity")
    public Page<EbookResponse> getEbookListByPopularity(@RequestParam int page, @RequestParam int size) {
        return ebookService.getEbookListByPopularity(page, size);
    }

    @GetMapping("/list/price/desc")
    public Page<EbookResponse> getEbookListByPriceDesc(@RequestParam int page, @RequestParam int size) {
        return ebookService.getEbookListSortedByPriceDesc(page, size);
    }

    @GetMapping("/list/price/asc")
    public Page<EbookResponse> getEbookListByPriceAsc(@RequestParam int page, @RequestParam int size) {
        return ebookService.getEbookListSortedByPriceAsc(page, size);
    }

    @GetMapping("/list/total")
    public int getTotalPage(@RequestParam int size) {
        return ebookService.getTotalPage(size);
    }

    @GetMapping("/list/total/category")
    public int getTotalPageByCategory(@RequestParam String category, @RequestParam int size) {
        return ebookService.getTotalPageByCategory(category, size);
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
