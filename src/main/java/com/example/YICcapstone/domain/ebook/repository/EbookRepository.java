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
    Page<Ebook> findAllByOrderByScoreAndUploadedAtDesc(Pageable pageable);
    Page<Ebook> findAllByEbookCategory_ClassificationOrderByUploadedAtDesc(String classification, Pageable pageable);
    Page<Ebook> findAllByOrderByPriceDesc(Pageable pageable);
    Page<Ebook> findAllByOrderByPriceAsc(Pageable pageable);
    Long countByEbookCategory_Classification(String classification);
}
