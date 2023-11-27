package com.example.YICcapstone.domain.basket.service;

import com.example.YICcapstone.domain.basket.dto.EbookBasketDto;
import com.example.YICcapstone.domain.basket.dto.VoiceModelBasketDto;
import com.example.YICcapstone.domain.basket.entity.EbookBasket;
import com.example.YICcapstone.domain.basket.entity.VoiceModelBasket;
import com.example.YICcapstone.domain.basket.exception.ProductAlreadyPutIntoBasketException;
import com.example.YICcapstone.domain.basket.exception.ProductNotExistIntoBasketException;
import com.example.YICcapstone.domain.basket.repository.EbookBasketRepository;
import com.example.YICcapstone.domain.basket.repository.VoiceModelBasketRepository;
import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.exception.EbookNotFoundException;
import com.example.YICcapstone.domain.ebook.repository.EbookRepository;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.exception.MemberNotExistException;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.purchase.domain.EbookPurchase;
import com.example.YICcapstone.domain.purchase.exception.EbookPurchaseAlreadyExistException;
import com.example.YICcapstone.domain.purchase.exception.VoiceModelPurchaseAlreadyExistException;
import com.example.YICcapstone.domain.purchase.repository.EbookPurchaseRepository;
import com.example.YICcapstone.domain.purchase.repository.VoiceModelPurchaseRepository;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.exception.VoiceModelNotFoundException;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelRepository;
import com.example.YICcapstone.global.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BasketServiceImpl implements BasketService {

    private final EbookRepository ebookRepository;
    private final EbookBasketRepository ebookBasketRepository;

    private final VoiceModelRepository voiceModelRepository;
    private final VoiceModelBasketRepository voiceModelBasketRepository;

    private final MemberRepository memberRepository;

    private final EbookPurchaseRepository ebookPurchaseRepository;
    private final VoiceModelPurchaseRepository voiceModelPurchaseRepository;

    @Override
    public List<EbookBasketDto> showEbookBasket() { // 유저의 장바구니 불러오기 (E-Book)
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());

        return ebookBasketRepository.findByMember(member)
                .stream()
                .map(ebookBasket -> new EbookBasketDto(ebookBasket))
                .collect(Collectors.toList());
    }
    @Override
    public List<VoiceModelBasketDto> showVoiceModelBasket() { // 유저의 장바구니 불러오기 (Voice Model)
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());

        List<VoiceModelBasket> voiceModelBasketList = voiceModelBasketRepository.findByMember(member);

        return voiceModelBasketRepository.findByMember(member)
                .stream()
                .map(voiceModelBasket -> new VoiceModelBasketDto(voiceModelBasket))
                .collect(Collectors.toList());
    }

    @Override
    public void putEbookInBasket(Long id) { // 선택한 품목 장바구니에 담기 (E-Book)
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());
        Ebook ebook = ebookRepository.findById(id)
                .orElseThrow(() -> new EbookNotFoundException());

        ebookPurchaseRepository.findByEbookIdAndMemberId(ebook.getId(), member.getId())
                .orElseThrow(() -> new EbookPurchaseAlreadyExistException());

        EbookBasket ebookBasket = EbookBasket.builder()
                .ebook(ebook).member(member).build();

        EbookBasket checkEbookBasket = ebookBasketRepository.findByMemberAndEbook(member, ebook);
        if(checkEbookBasket != null) { throw new ProductAlreadyPutIntoBasketException(); }

        ebookBasketRepository.save(ebookBasket);
    }
    @Override
    public void putVoiceModelInBasket(Long id) { // 선택한 품목 장바구니에 담기 (Voice Model)
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());
        VoiceModel voiceModel = voiceModelRepository.findById(id)
                .orElseThrow(() -> new VoiceModelNotFoundException());

        voiceModelPurchaseRepository.findByVoiceModelIdAndMemberId(voiceModel.getId(), member.getId())
                .orElseThrow(() -> new VoiceModelPurchaseAlreadyExistException());

        VoiceModelBasket voiceModelBasket = VoiceModelBasket.builder()
                        .voiceModel(voiceModel).member(member).build();

        VoiceModelBasket checkVoiceModelBasket = voiceModelBasketRepository.findByMemberAndVoiceModel(member, voiceModel);
        if(checkVoiceModelBasket != null) { throw new ProductAlreadyPutIntoBasketException(); }

        voiceModelBasketRepository.save(voiceModelBasket);
    }

    @Override
    public void deleteEbookFromBasket(Long id) { // 선택한 품목 장바구니에서 삭제 (E-Book)
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());
        Ebook ebook = ebookRepository.findById(id)
                .orElseThrow(() -> new EbookNotFoundException());

        EbookBasket ebookBasket = ebookBasketRepository.findByMemberAndEbook(member, ebook);
        if(ebookBasket == null) { throw new ProductNotExistIntoBasketException(); }

        ebookBasketRepository.delete(ebookBasket);
    }
    @Override
    public void deleteVoiceModelFromBasket(Long id) { // 선택한 품목 장바구니에서 삭제 (Voice-Model)
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());
        VoiceModel voiceModel = voiceModelRepository.findById(id)
                .orElseThrow(() -> new VoiceModelNotFoundException());

        VoiceModelBasket voiceModelBasket = voiceModelBasketRepository.findByMemberAndVoiceModel(member, voiceModel);
        if(voiceModelBasket == null) { throw new ProductNotExistIntoBasketException(); }

        voiceModelBasketRepository.delete(voiceModelBasket);
    }
}
