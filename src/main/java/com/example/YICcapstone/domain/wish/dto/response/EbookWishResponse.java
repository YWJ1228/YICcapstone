package com.example.YICcapstone.domain.wish.dto.response;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import com.example.YICcapstone.domain.ebook.dto.response.EbookResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
public class EbookWishResponse {
    private Long wishId;
    private EbookResponse ebook;

    public EbookWishResponse(Long wishId, Ebook ebook){
        this.wishId = wishId;
        this.ebook = new EbookResponse(ebook);
    }
}
