package com.example.YICcapstone.domain.search.repository;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchEbookRepository extends JpaRepository<Ebook, Long> {
    Page<Ebook> findByEbookNameContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Ebook> findByAuthorContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Ebook> findByPublisherContainingIgnoreCase(String keyword, Pageable pageable);
}
