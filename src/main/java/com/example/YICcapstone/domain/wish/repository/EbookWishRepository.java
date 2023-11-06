package com.example.YICcapstone.domain.wish.repository;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.wish.domain.EbookWish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EbookWishRepository extends JpaRepository<EbookWish, Long> {
    Optional<EbookWish> findByMemberIdAndEbookId(Long memberId, Long ebookId);
    Page<EbookWish> findAllByMemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);
    Boolean existsByMemberIdAndEbookId(Long memberId, Long ebookId);
}
