package com.example.YICcapstone.domain.wish.controller;

import com.example.YICcapstone.domain.wish.dto.response.EbookWishResponse;
import com.example.YICcapstone.domain.wish.dto.response.VoiceModelWishResponse;
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

    @PostMapping("/ebook/{ebookId}")
    public ResponseEntity<String> wishEbook(Long ebookId) {
        wishService.wishEbook(ebookId);
        return ResponseEntity.status(200).body("success");
    }

    @PostMapping("/voice-model/{voiceModelId}")
    public ResponseEntity<String> wishVoiceModel(Long voiceModelId) {
        wishService.wishVoiceModel(voiceModelId);
        return ResponseEntity.status(200).body("success");
    }

    @PostMapping("/ebook/{ebookId}/verify")
    public Boolean ebookWishVerify(Long ebookId) {
        return wishService.ebookWishVerify(ebookId);
    }

    @PostMapping("/voice-model/{voiceModelId}/verify")
    public Boolean voiceModelWishVerify(Long voiceModelId) {
        return wishService.voiceModelWishVerify(voiceModelId);
    }

    @GetMapping("/ebook")
    public Page<EbookWishResponse> getEbookWishList(@RequestParam int page) {
        return wishService.getEbookWishList(page);
    }

    @GetMapping("/voice-model")
    public Page<VoiceModelWishResponse> getVoiceModelWishList(@RequestParam int page) {
        return wishService.getVoiceModelWishList(page);
    }
}
