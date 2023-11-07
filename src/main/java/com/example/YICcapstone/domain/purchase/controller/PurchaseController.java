package com.example.YICcapstone.domain.purchase.controller;

import com.example.YICcapstone.domain.purchase.dto.request.PurchaseRequest;
import com.example.YICcapstone.domain.purchase.dto.response.PurchaseResponse;
import com.example.YICcapstone.domain.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/purchase")
@CrossOrigin("*")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/voice-model")
    public ResponseEntity<String> createVoiceModelPurchase(@RequestBody @Valid PurchaseRequest purchaseRequest) {
        purchaseService.createVoiceModelPurchase(purchaseRequest);
        return ResponseEntity.status(200).body("음성 모델 구매 성공");
    }

    @PostMapping("/ebook")
    public ResponseEntity<String> createEbookPurchase(@RequestBody @Valid PurchaseRequest purchaseRequest) {
        purchaseService.createEbookPurchase(purchaseRequest);
        return ResponseEntity.status(200).body("Ebook 구매 성공");
    }

    @GetMapping("/voice-model")
    public Page<PurchaseResponse> getVoiceModelPurchaseList(@RequestParam int page, @RequestParam int size) {
        return purchaseService.getVoiceModelPurchaseList(page, size);
    }

    @GetMapping("/ebook")
    public Page<PurchaseResponse> getEbookPurchaseList(@RequestParam int page, @RequestParam int size) {
        return purchaseService.getEbookPurchaseList(page, size);
    }

    @GetMapping("/voice-model/verify")
    public Boolean verifyVoiceModelPurchase(@RequestParam Long voiceModelId) {
        return purchaseService.isVoiceModelPurchased(voiceModelId);
    }

    @GetMapping("/ebook/verify")
    public Boolean verifyEbookPurchase(@RequestParam Long ebookId) {
        return purchaseService.isEbookPurchased(ebookId);
    }

    @GetMapping("/voice-model/total")
    public int getVoiceModelTotalPage(@RequestParam int size) {
        return purchaseService.getVoiceModelTotalPage(size);
    }

    @GetMapping("/ebook/total")
    public int getEbookTotalPage(@RequestParam int size) {
        return purchaseService.getEbookTotalPage(size);
    }

}
