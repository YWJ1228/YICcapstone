package com.example.YICcapstone.domain.ebook.domain;


import jakarta.persistence.*;

@Entity
public class EbookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String classification;
}
