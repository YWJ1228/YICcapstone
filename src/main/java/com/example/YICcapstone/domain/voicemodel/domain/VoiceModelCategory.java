package com.example.YICcapstone.domain.voicemodel.domain;

import jakarta.persistence.*;

@Entity
public class VoiceModelCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String job;
}
