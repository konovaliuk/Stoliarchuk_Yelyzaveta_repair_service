package com.example.cinemabookingsystem.Repository;

import com.example.cinemabookingsystem.Model.Feedback;
import com.example.cinemabookingsystem.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
    @Query("select f from Feedback f where f.request.id = :requestId")
    List<Feedback> findAllByRequestId(long requestId);
}
