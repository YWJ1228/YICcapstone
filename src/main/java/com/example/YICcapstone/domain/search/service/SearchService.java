package com.example.YICcapstone.domain.search.service;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {
    Page<Ebook> searchEbookName(String keyword, Pageable pageable);

    Page<Ebook> searchEbookAuthor(String keyword, Pageable pageable);

    Page<Ebook> searchEbookPublisher(String keyword, Pageable pageable);

    Page<VoiceModel> searchVoiceModelName(String keyword, Pageable pageable);
}
