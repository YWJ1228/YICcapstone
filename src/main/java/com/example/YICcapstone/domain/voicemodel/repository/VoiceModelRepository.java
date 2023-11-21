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
    @Query("SELECT v FROM VoiceModel v LEFT JOIN v.voiceModelPurchaseList vp " +
            "LEFT JOIN v.voiceModelPreferenceList vl " +
            "GROUP BY v.id " +
            "ORDER BY (v.viewCount + (COUNT(vp) * 10) + (COUNT(vl) * 5)) DESC, v.uploadedAt DESC")
    Page<VoiceModel> findAllByOrderByScoreAndUploadedAtDesc(Pageable pageable);
    Page<VoiceModel> findAllByOrderByPriceDescUploadedAtDesc(Pageable pageable);
    Page<VoiceModel> findAllByOrderByPriceAscUploadedAtDesc(Pageable pageable);

    // 카테고리 분류 리스트
    Page<VoiceModel> findAllByCategoryOrderByUploadedAtDesc(String category, Pageable pageable);
    @Query("SELECT v FROM VoiceModel v LEFT JOIN v.voiceModelPurchaseList vp " +
            "LEFT JOIN v.voiceModelPreferenceList vl " +
            "WHERE v.category = ?1 " +
            "GROUP BY v.id " +
            "ORDER BY (v.viewCount + (COUNT(vp) * 10) + (COUNT(vl) * 5)) DESC, v.uploadedAt DESC")
    Page<VoiceModel> findAllByCategoryOrderByScoreAndUploadedAtDesc(String category, Pageable pageable);
    Page<VoiceModel> findAllByCategoryOrderByPriceDescUploadedAtDesc(String category, Pageable pageable);
    Page<VoiceModel> findAllByCategoryOrderByPriceAscUploadedAtDesc(String category, Pageable pageable);
    Integer countByCategory(String category);
}
