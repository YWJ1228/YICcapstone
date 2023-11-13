package com.example.YICcapstone.domain.basket.repository;

import com.example.YICcapstone.domain.basket.entity.EbookBasket;
import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EbookBasketRepository extends JpaRepository<EbookBasket, Long> {

    @Query(value = "SELECT * FROM EBOOK_BASKET ORDER BY CREATED_AT DESC", nativeQuery = true)
    List<EbookBasket> findByMember(Member member);
    EbookBasket findByMemberAndEbook(Member member, Ebook ebook);
}
