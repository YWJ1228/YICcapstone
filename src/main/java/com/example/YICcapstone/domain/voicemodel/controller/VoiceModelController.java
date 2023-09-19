package com.example.YICcapstone.domain.voicemodel.controller;

import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.dto.request.VoiceModelCreationRequest;
import com.example.YICcapstone.domain.voicemodel.service.VoiceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/voice-model")
@RestController
public class VoiceModelController {
    @Autowired
    private VoiceModelService voiceModelService;

    @GetMapping("/{voiceModelId}")
    public VoiceModel getVoiceModel(@PathVariable Long voiceModelId) {
        return voiceModelService.getVoiceModel(voiceModelId);
    }

    @GetMapping("/list")
    public Page<VoiceModel> getVoiceModelList(@RequestParam int page) {
        return voiceModelService.getVoiceModelList(page);
    }

    @GetMapping("/list/category")
    public Page<VoiceModel> getVoiceModelListByCategory(@RequestParam String job, @RequestParam int page) {
        return voiceModelService.getVoiceModelListByCategory(job, page);
    }

    @PostMapping
    public ResponseEntity<String> createVoiceModel(@RequestBody VoiceModelCreationRequest voiceModelCreationRequest) {
        voiceModelService.createVoiceModel(voiceModelCreationRequest);
        return ResponseEntity.ok("VoiceModel created successfully");
    }

    @PutMapping("/{voiceModelId}")
    public ResponseEntity<String> updateVoiceModel(@PathVariable Long voiceModelId, @RequestBody VoiceModelCreationRequest voiceModelCreationRequest) {
        voiceModelService.updateVoiceModel(voiceModelId, voiceModelCreationRequest);
        return ResponseEntity.ok("VoiceModel updated successfully");
    }

    @DeleteMapping("/{voiceModelId}")
    public ResponseEntity<String> deleteVoiceModel(@PathVariable Long voiceModelId) {
        voiceModelService.deleteVoiceModel(voiceModelId);
        return ResponseEntity.ok("VoiceModel deleted successfully");
    }




}
