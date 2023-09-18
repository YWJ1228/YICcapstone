package com.example.YICcapstone.domain.ebook.repository;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long> {

}
