package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

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
    private long feedbackId;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private int rating;

    @Column(name = "feedback_description")
    private String feedbackDescription;
}
