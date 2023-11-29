package com.example.YICcapstone.domain.search.service;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.dto.response.EbookResponse;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.dto.response.VoiceModelResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {
    Page<EbookResponse> searchEbookName(String keyword, Pageable pageable);

    Page<EbookResponse> searchEbookAuthor(String keyword, Pageable pageable);

    Page<EbookResponse> searchEbookPublisher(String keyword, Pageable pageable);

    Page<VoiceModelResponse> searchVoiceModelName(String keyword, Pageable pageable);
}
