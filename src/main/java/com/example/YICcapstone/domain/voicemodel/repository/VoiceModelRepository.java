package com.example.YICcapstone.domain.voicemodel.repository;

import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoiceModelRepository extends JpaRepository<VoiceModel, Long> {
    Page<VoiceModel> findAllByOrderByUploadedAtDesc(Pageable pageable);
    @Query("SELECT vm FROM VoiceModel vm ORDER BY vm.viewCount + (vm.preferenceCount * 5) + (vm.purchaseCount * 10) DESC, vm.uploadedAt DESC")
    Page<VoiceModel> findAllByOrderByScoreAndUploadedAtDesc(Pageable pageable);
    Page<VoiceModel> findAllByVoiceModelCategory_JobOrderByUploadedAtDesc(String job, Pageable pageable);
    Page<VoiceModel> findAllByOrderByPriceDesc(Pageable pageable);
    Page<VoiceModel> findAllByOrderByPriceAsc(Pageable pageable);
    Long countByVoiceModelCategory_Job(String job);
}
