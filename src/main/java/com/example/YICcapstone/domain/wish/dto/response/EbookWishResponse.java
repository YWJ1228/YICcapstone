package com.example.YICcapstone.domain.wish.dto.response;

import com.example.YICcapstone.domain.ebook.domain.Ebook;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor
@Getter
public class EbookWishResponse {
    Long id;
    Ebook ebook;
}
