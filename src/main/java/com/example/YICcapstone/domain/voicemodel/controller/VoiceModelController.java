package com.example.YICcapstone.domain.voicemodel.controller;

import com.example.YICcapstone.domain.voicemodel.dto.request.VoiceModelCreationRequest;
import com.example.YICcapstone.domain.voicemodel.dto.response.VoiceModelResponse;
import com.example.YICcapstone.domain.voicemodel.service.VoiceModelPreferenceService;
import com.example.YICcapstone.domain.voicemodel.service.VoiceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/voice-model")
@CrossOrigin(origins = "*")
@RestController
public class VoiceModelController {
    @Autowired
    private VoiceModelService voiceModelService;
    @Autowired
    private VoiceModelPreferenceService voiceModelPreferenceService;
    @GetMapping("/{voiceModelId}")
    public VoiceModelResponse getVoiceModel(@PathVariable Long voiceModelId) {
        return voiceModelService.getVoiceModel(voiceModelId);
    }

    @GetMapping("/list")
    public Page<VoiceModelResponse> getVoiceModelList(@RequestParam int page, @RequestParam int size) {
        return voiceModelService.getVoiceModelList(page, size);
    }

    @GetMapping("/list/category")
    public Page<VoiceModelResponse> getVoiceModelListByCategory(@RequestParam String category, @RequestParam int page, @RequestParam int size) {
        return voiceModelService.getVoiceModelListByCategory(category, page, size);
    }

    @GetMapping("/list/category/popularity")
    public Page<VoiceModelResponse> getVoiceModelListByScoreAndUploadedAtDesc(@RequestParam String category, @RequestParam int page, @RequestParam int size) {
        return voiceModelService.getVoiceModelListByCategorySortedByScoreAndUploadedAtDesc(category, page, size);
    }

    @GetMapping("/list/category/price/desc")
    public Page<VoiceModelResponse> getVoiceModelListByPriceDesc(@RequestParam String category, @RequestParam int page, @RequestParam int size) {
        return voiceModelService.getVoiceModelListByCategorySortedByPriceDesc(category, page, size);
    }

    @GetMapping("/list/category/price/asc")
    public Page<VoiceModelResponse> getVoiceModelListByPriceAsc(@RequestParam String category, @RequestParam int page, @RequestParam int size) {
        return voiceModelService.getVoiceModelListByCategorySortedByPriceAsc(category, page, size);
    }

    @GetMapping("/list/popularity")
    public Page<VoiceModelResponse> getVoiceModelListByPopularity(@RequestParam int page, @RequestParam int size) {
        return voiceModelService.getVoiceModelListByPopularity(page, size);
    }

    @GetMapping("/list/price/desc")
    public Page<VoiceModelResponse> getVoiceModelListByPriceDesc(@RequestParam int page, @RequestParam int size) {
        return voiceModelService.getVoiceModelListByPriceDesc(page, size);
    }

    @GetMapping("/list/price/asc")
    public Page<VoiceModelResponse> getVoiceModelListByPriceAsc(@RequestParam int page, @RequestParam int size) {
        return voiceModelService.getVoiceModelListByPriceAsc(page, size);
    }

    @GetMapping("/list/total")
    public int getTotalPage(@RequestParam int size) {
        return voiceModelService.getTotalPage(size);
    }

    @GetMapping("/list/total/category")
    public int getTotalPageByCategory(@RequestParam String category, @RequestParam int size) {
        return voiceModelService.getTotalPageByCategory(category, size);
    }

    @PostMapping
    public ResponseEntity<String> createVoiceModel(@RequestBody @Valid VoiceModelCreationRequest voiceModelCreationRequest) {
        voiceModelService.createVoiceModel(voiceModelCreationRequest);
        return ResponseEntity.status(200).body("VoiceModel created successfully");
    }

    @PostMapping("/{voiceModelId}/preference")
    public ResponseEntity<Integer> createVoiceModelPreference(@PathVariable Long voiceModelId) {
        Integer preferenceCount = voiceModelPreferenceService.preferVoiceModel(voiceModelId);
        return ResponseEntity.status(200).body(preferenceCount);
    }

    @PostMapping("/{voiceModelId}/preference/verify")
    public Boolean preferenceVerify(@PathVariable Long voiceModelId){
        return voiceModelPreferenceService.preferenceVerify(voiceModelId);
    }

    @PutMapping("/{voiceModelId}")
    public ResponseEntity<String> updateVoiceModel(@PathVariable Long voiceModelId, @RequestBody @Valid VoiceModelCreationRequest voiceModelCreationRequest) {
        voiceModelService.updateVoiceModel(voiceModelId, voiceModelCreationRequest);
        return ResponseEntity.status(200).body("VoiceModel updated successfully");
    }

    @DeleteMapping("/{voiceModelId}")
    public ResponseEntity<String> deleteVoiceModel(@PathVariable Long voiceModelId) {
        voiceModelService.deleteVoiceModel(voiceModelId);
        return ResponseEntity.status(200).body("VoiceModel deleted successfully");
    }
    
}
