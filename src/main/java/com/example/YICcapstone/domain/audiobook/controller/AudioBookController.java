package com.example.YICcapstone.domain.audiobook.controller;

import com.example.YICcapstone.domain.audiobook.dto.AudioBookLinkDto;
import com.example.YICcapstone.domain.audiobook.dto.EbookAndVoiceModelDto;
import com.example.YICcapstone.domain.audiobook.entity.AudioBook;
import com.example.YICcapstone.domain.audiobook.service.AudioBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
public class AudioBookController {
    @Autowired
    AudioBookService audioBookService;

    @GetMapping("/api/user/audio-book/play") // E-Book과 음성모델에 매핑되는 오디오북 재생을 위한 URL 제공하기 (ebookId, voiceModelId)
    public ResponseEntity<AudioBookLinkDto> findAudioBook (@RequestBody EbookAndVoiceModelDto ebookAndVoiceModelDto) {
        AudioBookLinkDto audioBookLinkDto = audioBookService.findAudioBook(ebookAndVoiceModelDto);

        return ResponseEntity.status(HttpStatus.OK).body(audioBookLinkDto);
    }
}
