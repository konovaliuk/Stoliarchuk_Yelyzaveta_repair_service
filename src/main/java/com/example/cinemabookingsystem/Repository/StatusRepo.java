package com.example.cinemabookingsystem.Repository;

import com.example.cinemabookingsystem.Model.Role;
import com.example.cinemabookingsystem.Model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatusRepo extends JpaRepository<Status, Integer> {
    @Query("select s from Status s where s.name = :name")
    Status findByName(String name);
}