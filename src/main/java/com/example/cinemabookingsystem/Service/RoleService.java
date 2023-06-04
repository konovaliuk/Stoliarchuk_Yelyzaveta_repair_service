package com.example.cinemabookingsystem.Service;

import com.example.cinemabookingsystem.Model.Role;
import com.example.cinemabookingsystem.Model.User;
import com.example.cinemabookingsystem.Repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepo roleRepo;

    public Role getRole(int id) {
        return roleRepo.findById(id).orElse(null);
    }

    public Role getRoleByName(String name) {
        Role foundRole = roleRepo.findByName(name);
        if(foundRole == null)
            throw new IllegalArgumentException("No such role");
        return foundRole;
    }

    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    public Role createRole(Role role) {
        return roleRepo.save(role);
    }


    public void updateRole(int id, Role role) throws IllegalArgumentException {
        Optional<Role> maybeRoleToUpdate = roleRepo.findById(id);
        if(maybeRoleToUpdate.isEmpty())
            throw new IllegalArgumentException("Invalid role");

        Role userToUpdate = maybeRoleToUpdate.get();
        if(role.getName() != null && !role.getName().isBlank())
            userToUpdate.setName(role.getName());

        roleRepo.save(userToUpdate);
    }

    public void deleteRole(int id) {
        roleRepo.deleteById(id);
    }
}
