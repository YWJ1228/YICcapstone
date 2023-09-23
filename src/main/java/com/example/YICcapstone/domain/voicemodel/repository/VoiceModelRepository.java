package com.example.YICcapstone.domain.voicemodel.repository;

import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoiceModelRepository extends JpaRepository<VoiceModel, Long> {
    Page<VoiceModel> findAllByOrderByUploadedAtDesc(Pageable pageable);
    Page<VoiceModel> findAllByOrderByScoreDesc(Pageable pageable);
    Page<VoiceModel> findAllByVoiceModelCategory_JobOrderByUploadedAtDesc(String job, Pageable pageable);
}
