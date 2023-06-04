package com.example.cinemabookingsystem.Service;

import com.example.cinemabookingsystem.Model.Request;
import com.example.cinemabookingsystem.Model.Role;
import com.example.cinemabookingsystem.Model.Status;
import com.example.cinemabookingsystem.Model.User;
import com.example.cinemabookingsystem.Repository.RequestRepo;
import com.example.cinemabookingsystem.Repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RequestService {
    private final RequestRepo requestRepo;
    private final StatusService statusService;
    private final UserService userService;
    public List<Request> getAllRequests() {
        return requestRepo.findAll();
    }

    public Request getRequest(long requestId) {
        Optional<Request> maybeRequest = requestRepo.findById(requestId);
        if(maybeRequest.isEmpty())
            throw new IllegalArgumentException("No such request");
        return maybeRequest.get();
    }

    public List<Request> getRequestsByUserId(long userId) {
        List<Request> foundRequests = requestRepo.findAllByUserId(userId);
        if(foundRequests == null || foundRequests.isEmpty())
            throw new IllegalArgumentException("This user have not created any requests");
        return foundRequests;
    }

    public List<Request> getRequestsByStatus(String statusName) {
        List<Request> foundRequests = requestRepo.findAllByStatus(statusName);
        if(foundRequests == null || foundRequests.isEmpty())
            throw new IllegalArgumentException("No requests with this status");
        return foundRequests;
    }

    public Request createRequest(Long userId, Request request) {
        User foundUser = userService.getUserById(userId);
        if(foundUser == null)
            throw new IllegalArgumentException("User does not exist");

        request.setUser(foundUser);
        request.setStatus(statusService.getStatusByName("pending"));
        return requestRepo.save(request);
    }

    public void updateRequest(Long id, Request request) throws IllegalArgumentException {
        Optional<Request> maybeRequestToUpdate = requestRepo.findById(id);
        if(maybeRequestToUpdate.isEmpty())
            throw new IllegalArgumentException("Invalid request");

        Request requestToUpdate = maybeRequestToUpdate.get();
        if(request.getRequestDescription() != null && !request.getRequestDescription().isBlank())
            requestToUpdate.setRequestDescription(request.getRequestDescription());
        if(request.getProductName() != null && !request.getProductName().isBlank())
            requestToUpdate.setProductName(request.getProductName());
        if(request.getProductModel() != null && !request.getProductModel().isBlank())
            requestToUpdate.setProductModel(request.getProductModel());
        if(request.getDeclinationReason() != null && !request.getDeclinationReason().isBlank())
            requestToUpdate.setDeclinationReason(request.getDeclinationReason());
        if(request.getRepairCost() != null)
            requestToUpdate.setRepairCost(request.getRepairCost());

        requestRepo.save(requestToUpdate);
    }

    public void deleteRequest(long id) {
        requestRepo.deleteById(id);
    }
}
