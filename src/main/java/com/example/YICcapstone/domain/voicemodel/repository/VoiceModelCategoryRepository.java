package com.example.YICcapstone.domain.voicemodel.repository;

import com.example.YICcapstone.domain.voicemodel.domain.VoiceModelCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoiceModelCategoryRepository extends JpaRepository<VoiceModelCategory, Long> {
    Optional<VoiceModelCategory> findByJob(String job);
}
