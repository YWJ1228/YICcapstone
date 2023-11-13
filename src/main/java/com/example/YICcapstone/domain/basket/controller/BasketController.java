package com.example.YICcapstone.domain.basket.controller;

import com.example.YICcapstone.domain.basket.dto.EbookBasketDto;
import com.example.YICcapstone.domain.basket.dto.VoiceModelBasketDto;
import com.example.YICcapstone.domain.basket.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
public class BasketController {
    @Autowired
    private BasketService basketService;

    @GetMapping("/api/user/basket/e-book") // 유저의 장바구니 불러오기 (E-Book)
    public ResponseEntity<List<EbookBasketDto>> showEbookBasket() {
        List<EbookBasketDto> ebookBasketList = basketService.showEbookBasket();

        return ResponseEntity.status(HttpStatus.OK).body(ebookBasketList);
    }

    @GetMapping("/api/user/basket/voice-model") // 유저의 장바구니 불러오기 (Voice-model)
    public ResponseEntity<List<VoiceModelBasketDto>> showVoiceModelBasket() {
        List<VoiceModelBasketDto> voiceModelBasketList = basketService.showVoiceModelBasket();

        return ResponseEntity.status(HttpStatus.OK).body(voiceModelBasketList);
    }

    @PostMapping("/api/user/basket/e-book/{id}") // 선택한 품목 장바구니에 담기 (E-Book)
    public ResponseEntity<String> putEbookInBasket(@PathVariable Long id) {
        basketService.putEbookInBasket(id);

        return ResponseEntity.status(HttpStatus.OK).body("해당 e-book을 장바구니에 담았습니다!");
    }

    @PostMapping("/api/user/basket/voice-model/{id}") // 선택한 품목 장바구니에 담기 (Voice-Model)
    public ResponseEntity<String> putVoiceModelInBasket(@PathVariable Long id) {
        basketService.putVoiceModelInBasket(id);

        return ResponseEntity.status(HttpStatus.OK).body("해당 voice-model을 장바구니에 담았습니다!");
    }

    @DeleteMapping("/api/user/basket/e-book/{id}") // 선택한 품목 장바구니에서 삭제 (E-Book)
    public ResponseEntity<String> deleteEbookFromBasket(@PathVariable Long id) {
        basketService.deleteEbookFromBasket(id);

        return ResponseEntity.status(HttpStatus.OK).body("해당 e-book을 장바구니에서 삭제했습니다!");
    }

    @DeleteMapping("/api/user/basket/voice-model/{id}") // 선택한 품목 장바구니에서 삭제 (Voice-Model)
    public ResponseEntity<String> deleteVoiceModelFromBasket(@PathVariable Long id) {
        basketService.deleteVoiceModelFromBasket(id);

        return ResponseEntity.status(HttpStatus.OK).body("해당 voice-model을 장바구니에서 삭제했습니다!");
    }
}
