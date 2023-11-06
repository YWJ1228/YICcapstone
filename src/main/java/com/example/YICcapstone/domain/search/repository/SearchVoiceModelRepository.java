package com.example.YICcapstone.domain.search.repository;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchVoiceModelRepository extends JpaRepository<VoiceModel, Long> {
    Page<VoiceModel> findByCelebrityNameContainingIgnoreCase(String keyword, Pageable pageable);
}
