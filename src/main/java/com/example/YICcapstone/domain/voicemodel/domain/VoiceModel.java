package com.example.YICcapstone.domain.voicemodel.domain;

import com.example.YICcapstone.domain.voicemodel.dto.request.VoiceModelCreationRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Setter
@Getter
public class VoiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String voiceModelUrl;
    private String category;
    private String celebrityName;
    private int price;
    private String imageUrl;
    private String comment;
    private String sampleUrl;
    private int viewCount = 0;
    private int purchaseCount = 0;
    private LocalDateTime uploadedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "voiceModel")
    private List<VoiceModelPreference> voiceModelPreferenceList = new ArrayList<>();

    public VoiceModel(VoiceModelCreationRequest voiceModelCreationRequest){
        this.voiceModelUrl = voiceModelCreationRequest.getVoiceModelUrl();
        this.category = voiceModelCreationRequest.getCategory();
        this.celebrityName = voiceModelCreationRequest.getCelebrityName();
        this.price = voiceModelCreationRequest.getPrice();
        this.imageUrl = voiceModelCreationRequest.getImageUrl();
        this.comment = voiceModelCreationRequest.getComment();
        this.sampleUrl = voiceModelCreationRequest.getSampleUrl();
    }
    public void update(VoiceModelCreationRequest voiceModelCreationRequest){
        this.voiceModelUrl = voiceModelCreationRequest.getVoiceModelUrl();
        this.category = voiceModelCreationRequest.getCategory();
        this.celebrityName = voiceModelCreationRequest.getCelebrityName();
        this.price = voiceModelCreationRequest.getPrice();
        this.imageUrl = voiceModelCreationRequest.getImageUrl();
        this.comment = voiceModelCreationRequest.getComment();
        this.sampleUrl = voiceModelCreationRequest.getSampleUrl();
    }
}
