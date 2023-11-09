package com.example.YICcapstone.domain.basket.service;

import com.example.YICcapstone.domain.basket.dto.EbookBasketDto;
import com.example.YICcapstone.domain.basket.dto.VoiceModelBasketDto;
import com.example.YICcapstone.domain.basket.entity.VoiceModelBasket;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BasketService {
    List<EbookBasketDto> showEbookBasket(); // 유저의 장바구니 불러오기 (E-Book)
    List<VoiceModelBasketDto> showVoiceModelBasket(); // 유저의 장바구니 불러오기 (Voice Model)

    void putEbookInBasket(Long id); // 선택한 품목 장바구니에 담기 (E-Book)
    void putVoiceModelInBasket(Long id); // 선택한 품목 장바구니에 담기 (Voice Model)

    void deleteEbookFromBasket(Long id); // 선택한 품목 장바구니에서 삭제 (E-Book)
    void deleteVoiceModelFromBasket(Long id); // 선택한 품목 장바구니에서 삭제 (Voice-Model)
}
