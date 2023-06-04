package com.example.cinemabookingsystem.Controller;

import com.example.cinemabookingsystem.Model.Role;
import com.example.cinemabookingsystem.Model.User;
import com.example.cinemabookingsystem.Service.RoleService;
import com.example.cinemabookingsystem.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name){
        try {
            final Role role = roleService.getRoleByName(name);
            return ResponseEntity.ok(role);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createRole(@RequestBody Role role){
        Role roleAdded = roleService.createRole(role);
        String userURI = String.format("/roles/%d", roleAdded.getId());

        return ResponseEntity.created(URI.create(userURI)).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Void> updateRole(@PathVariable int id, @RequestBody Role role){
        try{
            roleService.updateRole(id, role);
            return ResponseEntity.noContent().build();
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
