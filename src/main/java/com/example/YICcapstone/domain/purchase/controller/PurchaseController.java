package com.example.YICcapstone.domain.purchase.controller;

import com.example.YICcapstone.domain.purchase.dto.request.PurchaseRequest;
import com.example.YICcapstone.domain.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/purchase")
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


}
