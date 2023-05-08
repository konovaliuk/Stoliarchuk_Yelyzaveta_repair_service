package dao.interfaces;

import entities.Role;

import java.util.List;

public interface RoleInterface {
    Role findById(long roleId);
    Role findByName(String name);
    List<Role> findAll();
    Role create(Role role);
    void delete(long roleId);
}
