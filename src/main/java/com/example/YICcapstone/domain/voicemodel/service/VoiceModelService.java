package com.example.YICcapstone.domain.voicemodel.service;

import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModelCategory;
import com.example.YICcapstone.domain.voicemodel.dto.request.VoiceModelCreationRequest;
import com.example.YICcapstone.domain.voicemodel.dto.response.VoiceModelResponse;
import com.example.YICcapstone.domain.voicemodel.exception.VoiceModelCategoryNotFoundException;
import com.example.YICcapstone.domain.voicemodel.exception.VoiceModelNotFoundException;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelCategoryRepository;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoiceModelService {
    @Autowired
    private VoiceModelRepository voiceModelRepository;
    @Autowired
    private VoiceModelCategoryRepository voiceModelCategoryRepository;

    @Transactional
    public void createVoiceModel(VoiceModelCreationRequest voiceModelCreationRequest) {
        VoiceModel voiceModel = new VoiceModel(voiceModelCreationRequest);
        VoiceModelCategory voiceModelCategory = voiceModelCategoryRepository.findByJob(voiceModelCreationRequest.getJob()).orElseThrow(
                VoiceModelCategoryNotFoundException::new
        );
        voiceModel.setVoiceModelCategory(voiceModelCategory);
        voiceModelRepository.save(voiceModel);
    }

    @Transactional
    public void updateVoiceModel(Long voiceModelId, VoiceModelCreationRequest voiceModelCreationRequest) {
        VoiceModel voiceModel = voiceModelRepository.findById(voiceModelId).orElseThrow(
                VoiceModelNotFoundException::new
        );
        VoiceModelCategory voiceModelCategory = voiceModelCategoryRepository.findByJob(voiceModelCreationRequest.getJob()).orElseThrow(
                VoiceModelCategoryNotFoundException::new
        );
        voiceModel.setVoiceModelCategory(voiceModelCategory);
        voiceModel.update(voiceModelCreationRequest);
        voiceModelRepository.save(voiceModel);
    }

    @Transactional
    public void deleteVoiceModel(Long voiceModelId) {
        VoiceModel voiceModel = voiceModelRepository.findById(voiceModelId).orElseThrow(
                VoiceModelNotFoundException::new
        );
        voiceModelRepository.delete(voiceModel);
    }

    @Transactional
    public VoiceModelResponse getVoiceModel(Long voiceModelId) {
        VoiceModel voiceModel = voiceModelRepository.findById(voiceModelId).orElseThrow(
                VoiceModelNotFoundException::new
        );

        voiceModel.setViewCount(voiceModel.getViewCount() + 1);
        return new VoiceModelResponse(voiceModel);
    }

    @Transactional(readOnly = true)
    public Page<VoiceModelResponse> getVoiceModelList(int page, int size) {
        return voiceModelRepository.findAllByOrderByUploadedAtDesc(PageRequest.of(page, size)).map(VoiceModelResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<VoiceModelResponse> getVoiceModelListByCategory(String job, int page, int size) {
        return voiceModelRepository.findAllByVoiceModelCategory_JobOrderByUploadedAtDesc(job, PageRequest.of(page, size)).map(VoiceModelResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<VoiceModelResponse> getVoiceModelListByPopularity(int page, int size){
        return voiceModelRepository.findAllByOrderByScoreAndUploadedAtDesc(PageRequest.of(page,size)).map(VoiceModelResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<VoiceModelResponse> getVoiceModelListByPriceDesc(int page, int size){
        return voiceModelRepository.findAllByOrderByPriceDesc(PageRequest.of(page, size)).map(VoiceModelResponse::new);
    }

    @Transactional(readOnly = true)
    public Page<VoiceModelResponse> getVoiceModelListByPriceAsc(int page, int size){
        return voiceModelRepository.findAllByOrderByPriceAsc(PageRequest.of(page, size)).map(VoiceModelResponse::new);
    }

    @Transactional(readOnly = true)
    public int getTotalPage(int size) {
        return (int) Math.ceil((double) voiceModelRepository.count() / size);
    }

    @Transactional(readOnly = true)
    public int getTotalPageByCategory(String job, int size) {
        return (int) Math.ceil((double) voiceModelRepository.countByVoiceModelCategory_Job(job) / size);
    }
}
