package org.stoliarchuk.dao.interfaces;

import org.stoliarchuk.entities.Role;

import java.util.List;

public interface RoleInterface {
    Role findById(long role_id);
    Role findByName(String name);
    List<Role> findAll();
    void createRole(Role role);
    void deleteById(long role_id);
}
