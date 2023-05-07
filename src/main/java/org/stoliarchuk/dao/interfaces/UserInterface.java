package org.stoliarchuk.dao.interfaces;

import org.stoliarchuk.entities.User;

import java.util.List;

public interface UserInterface {
    User findById(long userId);
    User findByEmail(String email);
    User findByUsername(String username);
    List<User> findAll();
    User createUser(User user);
    void updateUserById(long userId, User user);
    void deleteById(long userId);
}
