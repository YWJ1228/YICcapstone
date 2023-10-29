package com.example.YICcapstone.domain.ebook.repository;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long> {
    Page<Ebook> findAllByOrderByUploadedAtDesc(Pageable pageable);
    @Query("SELECT e FROM Ebook e ORDER BY e.viewCount + (e.purchaseCount * 10) DESC, e.uploadedAt DESC")
    Page<Ebook> findAllByOrderByScore(Pageable pageable);
    Page<Ebook> findAllByOrderByPriceDescUploadedAtDesc(Pageable pageable);
    Page<Ebook> findAllByOrderByPriceAscUploadedAtDesc(Pageable pageable);

    // 카테고리 분류 리스트
    Page<Ebook> findAllByCategoryOrderByUploadedAtDesc(String category, Pageable pageable);
    @Query("SELECT e FROM Ebook e WHERE e.category = ?1 ORDER BY e.viewCount + (e.purchaseCount * 10) DESC, e.uploadedAt DESC")
    Page<Ebook> findAllByCategoryOrderByScoreAndUploadedAtDesc(String category, Pageable pageable);
    Page<Ebook> findAllByCategoryOrderByPriceDescUploadedAtDesc(String category, Pageable pageable);
    Page<Ebook> findAllByCategoryOrderByPriceAscUploadedAtDesc(String category, Pageable pageable);
    Long countByCategory(String category);
}
