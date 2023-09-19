package com.example.YICcapstone.domain.ebook.repository;

import com.example.YICcapstone.domain.ebook.domain.EbookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EbookCategoryRepository extends JpaRepository<EbookCategory, Long> {
    Optional<EbookCategory> findByClassification(String classification);
}
