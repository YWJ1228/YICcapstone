package com.example.YICcapstone.domain.voicemodel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class VoiceModelCreationRequest {
    @NotBlank(message = "음성 모델 url을 지정해주세요.")
    @Size(min = 2)
    private String voiceModelUrl;
    @NotBlank(message = "음성 모델명을 입력해주세요.")
    @Size(min = 2)
    private String celebrityName;
    @NotBlank(message = "음성 모델 가격을 입력해주세요.")
    private int price;
    private String imageUrl;
    @NotBlank(message = "음성 모델 설명을 입력해주세요.")
    @Size(min = 2)
    private String comment;
    private String sampleUrl;
    @NotBlank(message = "카테고리를 선택해주세요.")
    private String job;
}
