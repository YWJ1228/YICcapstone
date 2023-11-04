package com.example.YICcapstone.domain.wish.controller;

import com.example.YICcapstone.domain.wish.dto.response.EbookWishResponse;
import com.example.YICcapstone.domain.wish.dto.response.VoiceModelWishResponse;
import com.example.YICcapstone.domain.wish.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/wish")
@CrossOrigin(origins = "*")
@RestController
public class WishController {
    @Autowired
    private WishService wishService;

    @PostMapping("/ebook")
    public ResponseEntity<String> wishEbook(@RequestParam Long ebookId) {
        wishService.wishEbook(ebookId);
        return ResponseEntity.status(200).body("success");
    }

    @PostMapping("/voice-model")
    public ResponseEntity<String> wishVoiceModel(@RequestParam Long voiceModelId) {
        wishService.wishVoiceModel(voiceModelId);
        return ResponseEntity.status(200).body("success");
    }

    @PostMapping("/ebook/{ebookId}/verify")
    public Boolean ebookWishVerify(@PathVariable Long ebookId) {
        return wishService.ebookWishVerify(ebookId);
    }

    @PostMapping("/voice-model/{voiceModelId}/verify")
    public Boolean voiceModelWishVerify(@PathVariable Long voiceModelId) {
        return wishService.voiceModelWishVerify(voiceModelId);
    }

    @GetMapping("/ebook")
    public Page<EbookWishResponse> getEbookWishList(@RequestParam int page, @RequestParam int size) {
        return wishService.getEbookWishList(page, size);
    }

    @GetMapping("/voice-model")
    public Page<VoiceModelWishResponse> getVoiceModelWishList(@RequestParam int page, @RequestParam int size) {
        return wishService.getVoiceModelWishList(page, size);
    }
}
