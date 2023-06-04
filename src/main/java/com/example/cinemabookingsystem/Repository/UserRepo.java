package com.example.cinemabookingsystem.Repository;

import com.example.cinemabookingsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("select u from User u where u.role.name = ?1")
    List<User> findByRole(String roleName);
    @Query("select u from User u where u.username = ?1")
    User findByUsername(String login);
    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);
}

