package com.example.YICcapstone.domain.ebook.domain;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class EbookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String classification;

    public EbookCategory(String classification) {
        this.classification = classification;
    }
}
