package com.example.YICcapstone.domain.search.service;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.dto.response.EbookResponse;
import com.example.YICcapstone.domain.search.repository.SearchEbookRepository;
import com.example.YICcapstone.domain.search.repository.SearchVoiceModelRepository;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.dto.response.VoiceModelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchServiceImpl implements SearchService {

    private final SearchEbookRepository searchEbookRepository;
    private final SearchVoiceModelRepository searchVoicemodelRepository;

    @Override
    public Page<EbookResponse> searchEbookName(String keyword, Pageable pageable) {
        Page<EbookResponse> eBookList = searchEbookRepository.findByEbookNameContainingIgnoreCase(keyword, pageable)
                .map(EbookResponse::new);
        return eBookList;
    }

    @Override
    public Page<EbookResponse> searchEbookAuthor(String keyword, Pageable pageable) {
        Page<EbookResponse> eBookList = searchEbookRepository.findByAuthorContainingIgnoreCase(keyword, pageable)
                .map(EbookResponse::new);
        return eBookList;
    }

    @Override
    public Page<EbookResponse> searchEbookPublisher(String keyword, Pageable pageable) {
        Page<EbookResponse> eBookList = searchEbookRepository.findByPublisherContainingIgnoreCase(keyword, pageable)
                .map(EbookResponse::new);
        return eBookList;
    }

    @Override
    public Page<VoiceModelResponse> searchVoiceModelName(String keyword, Pageable pageable) {
        Page<VoiceModelResponse> voiceModelList = searchVoicemodelRepository.findByCelebrityNameContainingIgnoreCase(keyword, pageable)
                .map(VoiceModelResponse::new);
        return voiceModelList;
    }
}
