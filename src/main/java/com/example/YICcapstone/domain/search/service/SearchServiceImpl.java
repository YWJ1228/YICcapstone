package com.example.YICcapstone.domain.search.service;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.search.repository.SearchEbookRepository;
import com.example.YICcapstone.domain.search.repository.SearchVoiceModelRepository;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
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
    public Page<Ebook> searchEbookName(String keyword, Pageable pageable) {
        Page<Ebook> eBookList = searchEbookRepository.findByEbookNameContainingIgnoreCase(keyword, pageable);
        return eBookList;
    }

    @Override
    public Page<Ebook> searchEbookAuthor(String keyword, Pageable pageable) {
        Page<Ebook> eBookList = searchEbookRepository.findByAuthorContainingIgnoreCase(keyword, pageable);
        return eBookList;
    }

    @Override
    public Page<Ebook> searchEbookPublisher(String keyword, Pageable pageable) {
        Page<Ebook> eBookList = searchEbookRepository.findByPublisherContainingIgnoreCase(keyword, pageable);
        return eBookList;
    }

    @Override
    public Page<VoiceModel> searchVoiceModelName(String keyword, Pageable pageable) {
        Page<VoiceModel> voiceModelList = searchVoicemodelRepository.findByCelebrityNameContainingIgnoreCase(keyword, pageable);
        return voiceModelList;
    }
}
