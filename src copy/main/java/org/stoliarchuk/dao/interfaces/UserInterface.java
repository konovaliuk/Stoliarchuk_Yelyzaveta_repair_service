package org.stoliarchuk.dao.interfaces;

import org.stoliarchuk.entities.User;

import java.util.List;

public interface UserInterface {
    User findById(long user_id);
    User findByEmail(String email);
    User findByUsername(String username);
    List<User> findAll();
    void createUser(User user);
    void updateUserById(long user_id, User user);
    void deleteById(long user_id);
}
