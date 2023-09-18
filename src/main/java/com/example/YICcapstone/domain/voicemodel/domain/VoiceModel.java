package com.example.YICcapstone.domain.voicemodel.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class VoiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String voiceModelUrl;
    private String celebrityName;
    private int price;
    private String imageUrl;
    private String comment;
    private String sampleUrl;
    private int viewCount = 0;
    private int purchaseCount = 0;
    private int preferenceCount = 0;
    private LocalDateTime uploadedAt = LocalDateTime.now();
}
