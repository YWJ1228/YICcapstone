package com.example.YICcapstone.domain.ebook.repository;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long> {
    Page<Ebook> findAllByOrderByUploadedAtDesc(Pageable pageable);
    Page<Ebook> findAllByOrderByScoreDesc(Pageable pageable);
    Page<Ebook> findAllByEbookCategory_ClassificationOrderByUploadedAtDesc(String classification, Pageable pageable);
}
