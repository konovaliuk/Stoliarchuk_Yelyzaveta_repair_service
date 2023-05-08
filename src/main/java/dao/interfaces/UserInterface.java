package dao.interfaces;

import entities.User;

import java.util.List;

public interface UserInterface {
    User findById(long userId);
    User findByEmail(String email);
    User findByUsername(String username);
    List<User> findAll();
    User create(User user);
    void update(User user);
    void delete(long userId);
}
