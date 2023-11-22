package com.example.YICcapstone.domain.feedback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 255, nullable = false)
    private String username;

    @Column(nullable = false)
    @Size(max = 20)
    private String title;

    @Column(nullable = false)
    @Size(max = 500)
    private String detail;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public void addUsername(String username) {this.username = username;}
}
