package com.example.cinemabookingsystem.Controller;

import com.example.cinemabookingsystem.Model.Role;
import com.example.cinemabookingsystem.Model.Status;
import com.example.cinemabookingsystem.Service.RoleService;
import com.example.cinemabookingsystem.Service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statuses")
public class StatusController {
    private final StatusService statusService;

    @GetMapping
    public ResponseEntity<List<Status>> getAllStatuses(){
        List<Status> statuses = statusService.getAllStatuses();
        return ResponseEntity.ok(statuses);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Status> getStatusByName(@PathVariable String name){
        try {
            final Status status = statusService.getStatusByName(name);
            return ResponseEntity.ok(status);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createRole(@RequestBody Status status){
        Status statusAdded = statusService.createStatus(status);
        String userURI = String.format("/statuses/%d", statusAdded.getId());

        return ResponseEntity.created(URI.create(userURI)).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Void> updateRole(@PathVariable int id, @RequestBody Status status){
        try{
            statusService.updateStatus(id, status);
            return ResponseEntity.noContent().build();
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        statusService.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }
}