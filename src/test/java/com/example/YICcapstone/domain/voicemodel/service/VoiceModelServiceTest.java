package com.example.YICcapstone.domain.voicemodel.service;

import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.dto.request.VoiceModelCreationRequest;
import com.example.YICcapstone.domain.voicemodel.dto.response.VoiceModelResponse;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class VoiceModelServiceTest {
    @Autowired
    VoiceModelService voiceModelService;
    @Autowired
    VoiceModelRepository voiceModelRepository;

    @BeforeEach
    void before() {

    }

    @Test
    @Transactional
    void 모델_생성() {
        // 1. 예상 데이터
        VoiceModelCreationRequest voiceModelCreationRequest = new VoiceModelCreationRequest(
                "testUrl", "testName",
                3000, "testImageUrl",
                "testComment", "testSampleUrl",
                "actor");
        voiceModelService.createVoiceModel(voiceModelCreationRequest);
        // 2. 실제 데이터
        VoiceModelResponse savedVoiceModel = voiceModelService.getVoiceModel(1L);
        // 3. 비교 및 검증
        assertEquals(1, savedVoiceModel.getId());
        assertEquals("testUrl", savedVoiceModel.getVoiceModelUrl());
        assertEquals("testName", savedVoiceModel.getCelebrityName());
        assertEquals(3000, savedVoiceModel.getPrice());
        assertEquals("testImageUrl", savedVoiceModel.getImageUrl());
        assertEquals("testComment", savedVoiceModel.getComment());
        assertEquals("testSampleUrl", savedVoiceModel.getSampleUrl());
        assertEquals("actor", savedVoiceModel.getCategory());
    }

    @Test
    @Transactional
    void 모델_수정() {
        // 1. 예상 데이터
        VoiceModelCreationRequest voiceModelCreationRequest = new VoiceModelCreationRequest(
                "testUrl", "testName",
                3000, "testImageUrl",
                "testComment", "testSampleUrl",
                "actor");
        VoiceModel createModel = voiceModelRepository.save( new VoiceModel(voiceModelCreationRequest));
        VoiceModelCreationRequest voiceModelCreationRequest2 = new VoiceModelCreationRequest(
                "testUrl2", "testName2",
                3000, "testImageUrl2",
                "testComment2", "testSampleUrl2",
                "singer");
        // 2. 실제 데이터
        voiceModelService.updateVoiceModel(createModel.getId(), voiceModelCreationRequest2);
        VoiceModel savedVoiceModel = voiceModelRepository.findById(createModel.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 모델이 없습니다."));
        List<VoiceModel> testVoice = voiceModelRepository.findAll();
        // 3. 비교 및 검증
        log.info("savedVoiceModelId: {}", savedVoiceModel.getId());
        for (VoiceModel voiceModel : testVoice) {
            log.info("testVoiceId: {}", voiceModel.getId());
        }
        assertEquals("testUrl2", savedVoiceModel.getVoiceModelUrl());
        assertEquals("testName2", savedVoiceModel.getCelebrityName());
        assertEquals(3000, savedVoiceModel.getPrice());
        assertEquals("testImageUrl2", savedVoiceModel.getImageUrl());
        assertEquals("testComment2", savedVoiceModel.getComment());
        assertEquals("testSampleUrl2", savedVoiceModel.getSampleUrl());
        assertEquals("singer", savedVoiceModel.getCategory());
    }

    @Test
    @Transactional
    void 모델_삭제() {
        // 1. 예상 데이터
        VoiceModelCreationRequest voiceModelCreationRequest = new VoiceModelCreationRequest(
                "testUrl", "testName",
                3000, "testImageUrl",
                "testComment", "testSampleUrl",
                "actor");
        VoiceModel createModel = voiceModelRepository.save( new VoiceModel(voiceModelCreationRequest));
        // 2. 실제 데이터
        voiceModelService.deleteVoiceModel(createModel.getId());
        // 3. 비교 및 검증
        assertEquals(0, voiceModelService.getVoiceModelList(0, 10).getTotalElements());
    }

    @Test
    @Transactional
    void 모델_상세페이지() {
        // 1. 예상 데이터
        VoiceModelCreationRequest voiceModelCreationRequest = new VoiceModelCreationRequest(
                "testUrl", "testName",
                3000, "testImageUrl",
                "testComment", "testSampleUrl",
                "actor");
        voiceModelService.createVoiceModel(voiceModelCreationRequest);
        VoiceModel createModel = voiceModelRepository.findAll().get(0);
        // 2. 실제 데이터
        VoiceModelResponse savedVoiceModel = voiceModelService.getVoiceModel(createModel.getId());
        // 3. 비교 및 검증
        assertEquals(createModel.getId(), savedVoiceModel.getId());
        assertEquals("testUrl", savedVoiceModel.getVoiceModelUrl());
        assertEquals("testName", savedVoiceModel.getCelebrityName());
        assertEquals(3000, savedVoiceModel.getPrice());
        assertEquals("testImageUrl", savedVoiceModel.getImageUrl());
        assertEquals("testComment", savedVoiceModel.getComment());
        assertEquals("testSampleUrl", savedVoiceModel.getSampleUrl());
        assertEquals("actor", savedVoiceModel.getCategory());
    }

    @Test
    @Transactional
    void 모델_리스트() {
        // 1. 예상 데이터
        VoiceModelCreationRequest voiceModelCreationRequest = new VoiceModelCreationRequest(
                "testUrl", "testName",
                3000, "testImageUrl",
                "testComment", "testSampleUrl",
                "actor");
        VoiceModelCreationRequest voiceModelCreationRequest2 = new VoiceModelCreationRequest(
                "testUrl2", "testName2",
                3000, "testImageUrl2",
                "testComment2", "testSampleUrl2",
                "singer");
        VoiceModelCreationRequest voiceModelCreationRequest3 = new VoiceModelCreationRequest(
                "testUrl3", "testName3",
                3000, "testImageUrl3",
                "testComment3", "testSampleUrl3",
                "narrator");
        voiceModelService.createVoiceModel(voiceModelCreationRequest);
        voiceModelService.createVoiceModel(voiceModelCreationRequest2);
        voiceModelService.createVoiceModel(voiceModelCreationRequest3);
        // 2. 실제 데이터
        Page<VoiceModelResponse> savedVoiceModelList = voiceModelService.getVoiceModelList(0, 10);
        // 3. 비교 및 검증
        assertEquals(3, savedVoiceModelList.getTotalElements());

        assertEquals("testUrl3", savedVoiceModelList.getContent().get(0).getVoiceModelUrl());
        assertEquals("testName3", savedVoiceModelList.getContent().get(0).getCelebrityName());
        assertEquals(3000, savedVoiceModelList.getContent().get(0).getPrice());
        assertEquals("testImageUrl3", savedVoiceModelList.getContent().get(0).getImageUrl());
        assertEquals("testComment3", savedVoiceModelList.getContent().get(0).getComment());
        assertEquals("testSampleUrl3", savedVoiceModelList.getContent().get(0).getSampleUrl());
        assertEquals("narrator", savedVoiceModelList.getContent().get(0).getCategory());

        assertEquals("testUrl2", savedVoiceModelList.getContent().get(1).getVoiceModelUrl());
        assertEquals("testName2", savedVoiceModelList.getContent().get(1).getCelebrityName());
        assertEquals(3000, savedVoiceModelList.getContent().get(1).getPrice());
        assertEquals("testImageUrl2", savedVoiceModelList.getContent().get(1).getImageUrl());
        assertEquals("testComment2", savedVoiceModelList.getContent().get(1).getComment());
        assertEquals("testSampleUrl2", savedVoiceModelList.getContent().get(1).getSampleUrl());
        assertEquals("singer", savedVoiceModelList.getContent().get(1).getCategory());

        assertEquals("testUrl", savedVoiceModelList.getContent().get(2).getVoiceModelUrl());
        assertEquals("testName", savedVoiceModelList.getContent().get(2).getCelebrityName());
        assertEquals(3000, savedVoiceModelList.getContent().get(2).getPrice());
        assertEquals("testImageUrl", savedVoiceModelList.getContent().get(2).getImageUrl());
        assertEquals("testComment", savedVoiceModelList.getContent().get(2).getComment());
        assertEquals("testSampleUrl", savedVoiceModelList.getContent().get(2).getSampleUrl());
        assertEquals("actor", savedVoiceModelList.getContent().get(2).getCategory());
    }

    @Test
    @Transactional
    void 모델_카테고리별리스트() {
        // 1. 예상 데이터
        VoiceModelCreationRequest voiceModelCreationRequest = new VoiceModelCreationRequest(
                "testUrl", "testName",
                3000, "testImageUrl",
                "testComment", "testSampleUrl",
                "actor");
        VoiceModelCreationRequest voiceModelCreationRequest2 = new VoiceModelCreationRequest(
                "testUrl2", "testName2",
                3000, "testImageUrl2",
                "testComment2", "testSampleUrl2",
                "singer");
        VoiceModelCreationRequest voiceModelCreationRequest3 = new VoiceModelCreationRequest(
                "testUrl3", "testName3",
                3000, "testImageUrl3",
                "testComment3", "testSampleUrl3",
                "narrator");
        voiceModelService.createVoiceModel(voiceModelCreationRequest);
        voiceModelService.createVoiceModel(voiceModelCreationRequest2);
        voiceModelService.createVoiceModel(voiceModelCreationRequest3);
        // 2. 실제 데이터
        Page<VoiceModelResponse> savedVoiceModeByActorsList = voiceModelService.getVoiceModelListByCategory("actor", 0, 10);
        Page<VoiceModelResponse> savedVoiceModeBySingersList = voiceModelService.getVoiceModelListByCategory("singer", 0, 10);
        Page<VoiceModelResponse> savedVoiceModeByNarratorsList = voiceModelService.getVoiceModelListByCategory("narrator", 0, 10);
        // 3. 비교 및 검증
        assertEquals(1, savedVoiceModeByActorsList.getTotalElements());
        assertEquals(1, savedVoiceModeBySingersList.getTotalElements());
        assertEquals(1, savedVoiceModeByNarratorsList.getTotalElements());
    }
}