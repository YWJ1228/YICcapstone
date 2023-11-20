package com.example.YICcapstone.domain.audiobook.repository;

import com.example.YICcapstone.domain.audiobook.dto.AudioBookLinkDto;
import com.example.YICcapstone.domain.audiobook.entity.AudioBook;
import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.voicemodel.domain.VoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioBookRepository extends JpaRepository<AudioBook, Long> {
    AudioBook findByEbookAndVoiceModel(Ebook ebook, VoiceModel voiceModel);
}
