package com.example.YICcapstone.domain.wish.controller;

import com.example.YICcapstone.domain.wish.domain.EbookWish;
import com.example.YICcapstone.domain.wish.domain.VoiceModelWish;
import com.example.YICcapstone.domain.wish.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/wish")
@RestController
public class WishController {
    @Autowired
    private WishService wishService;

    @PostMapping("/{ebookId}/wish")
    public ResponseEntity<String> wishEbook(Long ebookId) {
        wishService.wishEbook(ebookId);
        return ResponseEntity.status(200).body("success");
    }

    @PostMapping("/{voiceModelId}/wish")
    public ResponseEntity<String> wishVoiceModel(Long voiceModelId) {
        wishService.wishVoiceModel(voiceModelId);
        return ResponseEntity.status(200).body("success");
    }

    @PostMapping("/{ebookId}/wish/verify")
    public Boolean ebookWishVerify(Long ebookId) {
        return wishService.ebookWishVerify(ebookId);
    }

    @PostMapping("/{voiceModelId}/wish/verify")
    public Boolean voiceModelWishVerify(Long voiceModelId) {
        return wishService.voiceModelWishVerify(voiceModelId);
    }

    @GetMapping("/ebook")
    public Page<EbookWish> getEbookWishList(@RequestParam int page) {
        return wishService.getEbookWishList(page);
    }

    @GetMapping("/voice-model")
    public Page<VoiceModelWish> getVoiceModelWishList(@RequestParam int page) {
        return wishService.getVoiceModelWishList(page);
    }
}
