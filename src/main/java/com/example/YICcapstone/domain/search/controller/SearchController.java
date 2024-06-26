package com.example.YICcapstone.domain.search.controller;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.dto.response.EbookResponse;
import com.example.YICcapstone.domain.search.repository.SearchEbookRepository;
import com.example.YICcapstone.domain.search.repository.SearchVoiceModelRepository;
import com.example.YICcapstone.domain.search.service.SearchService;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.dto.response.VoiceModelResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private SearchEbookRepository searchEbookRepository;
    @Autowired
    private SearchVoiceModelRepository searchVoicemodelRepository;

    @GetMapping("/ebook/search/name") // E-Book 이름 오름차순으로 페이지 형식 검색 결과 불러오기
    public ResponseEntity<Page<EbookResponse>> searchEbookName(@RequestParam String keyword,
                                                  @PageableDefault(size = 10, sort = "ebookName", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<EbookResponse> searchList = searchService.searchEbookName(keyword, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(searchList);
    }

    @GetMapping("/ebook/search/author") // E-Book 작가 오름차순으로 페이지 형식 검색 결과 불러오기
    public ResponseEntity<Page<EbookResponse>> searchEbookAuthor(@RequestParam String keyword,
                                                   @PageableDefault(size = 10, sort = "author", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<EbookResponse> searchList = searchService.searchEbookAuthor(keyword, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(searchList);
    }

    @GetMapping("/ebook/search/publisher") // E-Book 출판사 오름차순으로 페이지 형식 검색 결과 불러오기
    public ResponseEntity<Page<EbookResponse>> searchEbookPublisher(@RequestParam String keyword,
                                                         @PageableDefault(size = 10, sort = "publisher", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<EbookResponse> searchList = searchService.searchEbookPublisher(keyword, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(searchList);
    }

    @GetMapping("/voice-model/search/name") // voice-model 이름 오름차순으로 페이지 형식 검색 결과 불러오기
    public ResponseEntity<Page<VoiceModelResponse>> searchVoiceModelName(@RequestParam String keyword,
                                                       @PageableDefault(size = 10, sort = "celebrityName", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<VoiceModelResponse> searchList = searchService.searchVoiceModelName(keyword, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(searchList);
    }
}
