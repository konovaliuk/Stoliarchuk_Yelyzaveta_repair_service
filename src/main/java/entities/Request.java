package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "requests")
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private long requestId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "request_description")
    private String requestDescription;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_model")
    private String productModel;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "repair_cost")
    private int repairCost;

    @Column(name = "declination_reason")
    private String declinationReason;
}