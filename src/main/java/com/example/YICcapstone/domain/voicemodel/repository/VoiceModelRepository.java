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
    Page<VoiceModel> findAllByOrderByPriceDescUploadedAtDesc(Pageable pageable);
    Page<VoiceModel> findAllByOrderByPriceAscUploadedAtDesc(Pageable pageable);

    // 카테고리 분류 리스트
    Page<VoiceModel> findAllByVoiceModelCategory_JobOrderByUploadedAtDesc(String job, Pageable pageable);
    @Query("SELECT vm FROM VoiceModel vm WHERE vm.voiceModelCategory.job = ?1 ORDER BY vm.viewCount + (vm.preferenceCount * 5) + (vm.purchaseCount * 10) DESC, vm.uploadedAt DESC")
    Page<VoiceModel> findAllByVoiceModelCategory_JobOrderByScoreAndUploadedAtDesc(String job, Pageable pageable);
    Page<VoiceModel> findAllByVoiceModelCategory_JobOrderByPriceDescUploadedAtDesc(String job, Pageable pageable);
    Page<VoiceModel> findAllByVoiceModelCategory_JobOrderByPriceAscUploadedAtDesc(String job, Pageable pageable);
    Long countByVoiceModelCategory_Job(String job);
}
