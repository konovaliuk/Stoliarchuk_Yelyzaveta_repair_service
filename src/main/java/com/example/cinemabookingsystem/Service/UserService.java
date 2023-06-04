package com.example.cinemabookingsystem.Service;

import com.example.cinemabookingsystem.Model.User;
import com.example.cinemabookingsystem.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;
    private final RoleService roleService;

    public User register(User user){
        user.setRole(roleService.getRoleByName("user"));
        if (userRepo.findByUsername(user.getUsername()) != null || userRepo.findByEmail(user.getEmail()) != null) {
            return null;
        }
        return userRepo.save(user);
    }

    public void changeRole(long id, int roleId) {
        User user = userRepo.findById(id).orElse(null);
        if (user!=null && !user.getRole().getName().equals("admin")) {
            user.setRole(roleService.getRole(roleId));
            userRepo.save(user);
        }
    }

    public List<User> getUsersByRole(String roleName) {
        return userRepo.findByRole(roleName);
    }

    public User login(String email, String password) throws IllegalArgumentException {
        User userToLogin = userRepo.findByEmail(email);
        if(password.equals(userToLogin.getPassword()))
            return userToLogin;
        else
            throw new IllegalArgumentException("Invalid email or password");
    }

    public void updateUser(Long id, User user) throws IllegalArgumentException {
        Optional<User> maybeUserToUpdate = userRepo.findById(id);
        if(maybeUserToUpdate.isEmpty())
            throw new IllegalArgumentException("Invalid user");

        User userToUpdate = maybeUserToUpdate.get();
        if(user.getEmail() != null && !user.getEmail().isBlank())
            userToUpdate.setEmail(user.getEmail());
        if(user.getUsername() != null && !user.getUsername().isBlank())
            userToUpdate.setUsername(user.getUsername());
        if(user.getPassword() != null && !user.getPassword().isBlank())
            userToUpdate.setPassword(user.getPassword());

        userRepo.save(userToUpdate);
    }

    public User getUserById(Long id) throws IllegalArgumentException{
        Optional<User> maybeUser = userRepo.findById(id);
        if(maybeUser.isEmpty())
            throw new IllegalArgumentException("No such user");
        return maybeUser.get();
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }
}
