package com.example.cinemabookingsystem.Repository;

import com.example.cinemabookingsystem.Model.Request;
import com.example.cinemabookingsystem.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepo extends JpaRepository<Request, Long> {
    @Query("select r from Request r where r.user.id = :userId")
    List<Request> findAllByUserId(long userId);

    @Query("select r from Request r where r.status.name = :statusName")
    List<Request> findAllByStatus(String statusName);
}