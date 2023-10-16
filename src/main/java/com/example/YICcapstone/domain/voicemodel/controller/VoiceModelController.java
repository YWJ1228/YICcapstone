package com.example.YICcapstone.domain.voicemodel.controller;

import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.dto.request.VoiceModelCreationRequest;
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

    @GetMapping("/{voiceModelId}")
    public VoiceModel getVoiceModel(@PathVariable Long voiceModelId) {
        return voiceModelService.getVoiceModel(voiceModelId);
    }

    @GetMapping("/list")
    public Page<VoiceModel> getVoiceModelList(@RequestParam int page, @RequestParam int size) {
        return voiceModelService.getVoiceModelList(page, size);
    }

    @GetMapping("/list/category")
    public Page<VoiceModel> getVoiceModelListByCategory(@RequestParam String job, @RequestParam int page, @RequestParam int size) {
        return voiceModelService.getVoiceModelListByCategory(job, page, size);
    }

    @GetMapping("/list/popularity")
    public Page<VoiceModel> getVoiceModelListByPopularity(@RequestParam int page, @RequestParam int size) {
        return voiceModelService.getVoiceModelListByPopularity(page, size);
    }

    @GetMapping("/list/price/desc")
    public Page<VoiceModel> getVoiceModelListByPriceDesc(@RequestParam int page, @RequestParam int size) {
        return voiceModelService.getVoiceModelListByPriceDesc(page, size);
    }

    @GetMapping("/list/price/asc")
    public Page<VoiceModel> getVoiceModelListByPriceAsc(@RequestParam int page, @RequestParam int size) {
        return voiceModelService.getVoiceModelListByPriceAsc(page, size);
    }

    @GetMapping("/list/total")
    public int getTotalPage(@RequestParam int size) {
        return voiceModelService.getTotalPage(size);
    }

    @PostMapping
    public ResponseEntity<String> createVoiceModel(@RequestBody @Valid VoiceModelCreationRequest voiceModelCreationRequest) {
        voiceModelService.createVoiceModel(voiceModelCreationRequest);
        return ResponseEntity.status(200).body("VoiceModel created successfully");
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
