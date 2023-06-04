package com.example.cinemabookingsystem.Controller;

import com.example.cinemabookingsystem.Model.Request;
import com.example.cinemabookingsystem.Model.User;
import com.example.cinemabookingsystem.Service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/requests")
public class RequestController {
    private final RequestService requestService;

    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests(@RequestParam(required = false) Integer userId, @RequestParam(required = false) String status){
        try {
            if (userId != null) {
                final List<Request> requests = requestService.getRequestsByUserId(userId);
                return ResponseEntity.ok(requests);
            }

            if (status != null && !status.isBlank()) {
                final List<Request> requests = requestService.getRequestsByStatus(status);
                return ResponseEntity.ok(requests);
            }

            List<Request> requests = requestService.getAllRequests();
            return ResponseEntity.ok(requests);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id){
        try {
            final Request request = requestService.getRequest(id);
            return ResponseEntity.ok(request);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createRequest(@RequestBody Request request, @RequestParam Long userId){
        try {
            Request requestAdded = requestService.createRequest(userId, request);
            String userURI = String.format("/requests/%d", requestAdded.getId());
            return ResponseEntity.created(URI.create(userURI)).build();
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Void> changeRequest(@PathVariable Long id, @RequestBody Request request){
        try{
            requestService.updateRequest(id, request);
            return ResponseEntity.noContent().build();
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}
