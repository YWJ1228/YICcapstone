package com.example.YICcapstone.domain.audiobook.service;

import com.example.YICcapstone.domain.audiobook.dto.AudioBookLinkDto;
import com.example.YICcapstone.domain.audiobook.dto.EbookAndVoiceModelDto;
import com.example.YICcapstone.domain.audiobook.entity.AudioBook;
import com.example.YICcapstone.domain.audiobook.exception.AudioBookNotFoundException;
import com.example.YICcapstone.domain.audiobook.repository.AudioBookRepository;
import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.exception.EbookNotFoundException;
import com.example.YICcapstone.domain.ebook.repository.EbookRepository;
import com.example.YICcapstone.domain.member.entity.Member;
import com.example.YICcapstone.domain.member.exception.MemberNotExistException;
import com.example.YICcapstone.domain.member.repository.MemberRepository;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import com.example.YICcapstone.domain.voicemodel.exception.VoiceModelNotFoundException;
import com.example.YICcapstone.domain.voicemodel.repository.VoiceModelRepository;
import com.example.YICcapstone.global.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AudioBookServiceImpl implements AudioBookService {

    private final AudioBookRepository audioBookRepository;
    private final MemberRepository memberRepository;
    private final EbookRepository ebookRepository;
    private final VoiceModelRepository voiceModelRepository;

    @Override
    public AudioBookLinkDto findAudioBook(EbookAndVoiceModelDto ebookAndVoiceModelDto) { // E-Book과 음성모델에 매핑되는 오디오북 재생을 위한 URL 제공하기 (ebookId, voiceModelId)
        Member member = memberRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new MemberNotExistException());
        Ebook ebook = ebookRepository.findById(ebookAndVoiceModelDto.ebookId())
                .orElseThrow(() -> new EbookNotFoundException());
        VoiceModel voiceModel = voiceModelRepository.findById(ebookAndVoiceModelDto.voiceModelId())
                .orElseThrow(() -> new VoiceModelNotFoundException());

        AudioBook findAudioBook
                = audioBookRepository.findByEbookAndVoiceModel(ebook,voiceModel);
        if(findAudioBook == null) { throw new AudioBookNotFoundException();}

        AudioBookLinkDto audioBookLinkDto = AudioBookLinkDto.builder()
                .audioBook(findAudioBook).build();

        return audioBookLinkDto;
    }
}
