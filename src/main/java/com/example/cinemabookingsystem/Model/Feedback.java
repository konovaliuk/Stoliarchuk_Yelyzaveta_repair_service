package com.example.cinemabookingsystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "feedbacks")
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private Integer rating;

    @Column(name = "feedback_description")
    private String feedbackDescription;
}