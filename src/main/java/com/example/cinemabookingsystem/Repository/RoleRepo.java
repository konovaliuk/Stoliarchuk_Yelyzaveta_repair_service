package com.example.cinemabookingsystem.Repository;

import com.example.cinemabookingsystem.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    @Query("select r from Role r where r.name = :name")
    Role findByName(String name);
}
