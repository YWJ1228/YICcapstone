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
    @Query("SELECT vm, (vm.viewCount + (vm.purchaseCount * 10) + (COUNT(vmp) * 5)) AS popularity " +
            "FROM VoiceModel vm " +
            "LEFT JOIN VoiceModelPreference vmp ON vm.id = vmp.voiceModel.id " +
            "GROUP BY vm " +
            "ORDER BY popularity DESC")
    Page<VoiceModel> findAllByOrderByScoreAndUploadedAtDesc(Pageable pageable);
    Page<VoiceModel> findAllByOrderByPriceDescUploadedAtDesc(Pageable pageable);
    Page<VoiceModel> findAllByOrderByPriceAscUploadedAtDesc(Pageable pageable);

    // 카테고리 분류 리스트
    Page<VoiceModel> findAllByCategoryOrderByUploadedAtDesc(String category, Pageable pageable);
    @Query("SELECT vm, (vm.viewCount + (vm.purchaseCount * 10) + (COUNT(vmp) * 5)) AS popularity " +
            "FROM VoiceModel vm " +
            "LEFT JOIN VoiceModelPreference vmp ON vm.id = vmp.voiceModel.id " +
            "WHERE vm.category = :category " +
            "GROUP BY vm " +
            "ORDER BY popularity DESC")
    Page<VoiceModel> findAllByCategoryOrderByScoreAndUploadedAtDesc(String category, Pageable pageable);
    Page<VoiceModel> findAllByCategoryOrderByPriceDescUploadedAtDesc(String category, Pageable pageable);
    Page<VoiceModel> findAllByCategoryOrderByPriceAscUploadedAtDesc(String category, Pageable pageable);
    Integer countByCategory(String category);
}
