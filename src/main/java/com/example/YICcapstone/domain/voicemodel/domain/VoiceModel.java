package com.example.YICcapstone.domain.voicemodel.domain;

import com.example.YICcapstone.domain.voicemodel.dto.request.VoiceModelCreationRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Setter
@Getter
public class VoiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voice_model_category_id")
    private VoiceModelCategory voiceModelCategory;

    @Column
    private String voiceModelUrl;
    private String celebrityName;
    private int price;
    private String imageUrl;
    private String comment;
    private String sampleUrl;
    private int viewCount = 0;
    private int purchaseCount = 0;
    private LocalDateTime uploadedAt = LocalDateTime.now();

    public VoiceModel(VoiceModelCreationRequest voiceModelCreationRequest){
        this.voiceModelUrl = voiceModelCreationRequest.getVoiceModelUrl();
        this.celebrityName = voiceModelCreationRequest.getCelebrityName();
        this.price = voiceModelCreationRequest.getPrice();
        this.imageUrl = voiceModelCreationRequest.getImageUrl();
        this.comment = voiceModelCreationRequest.getComment();
        this.sampleUrl = voiceModelCreationRequest.getSampleUrl();
    }
    public void update(VoiceModelCreationRequest voiceModelCreationRequest){
        this.voiceModelUrl = voiceModelCreationRequest.getVoiceModelUrl();
        this.celebrityName = voiceModelCreationRequest.getCelebrityName();
        this.price = voiceModelCreationRequest.getPrice();
        this.imageUrl = voiceModelCreationRequest.getImageUrl();
        this.comment = voiceModelCreationRequest.getComment();
        this.sampleUrl = voiceModelCreationRequest.getSampleUrl();
    }
}
