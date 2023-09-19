package com.example.YICcapstone.domain.voicemodel.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class VoiceModelCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String job;

    public VoiceModelCategory(String job) {
        this.job = job;
    }
}
