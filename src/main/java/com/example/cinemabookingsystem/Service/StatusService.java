package com.example.cinemabookingsystem.Service;

import com.example.cinemabookingsystem.Model.Role;
import com.example.cinemabookingsystem.Model.Status;
import com.example.cinemabookingsystem.Repository.RoleRepo;
import com.example.cinemabookingsystem.Repository.StatusRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StatusService {
    private final StatusRepo statusRepo;

    public Status getStatus(int id) {
        return statusRepo.findById(id).orElse(null);
    }

    public Status getStatusByName(String name) {
        Status foundStatus = statusRepo.findByName(name);
        if(foundStatus == null)
            throw new IllegalArgumentException("No such status");
        return foundStatus;
    }

    public List<Status> getAllStatuses() {
        return statusRepo.findAll();
    }

    public Status createStatus(Status status) {
        return statusRepo.save(status);
    }


    public void updateStatus(int id, Status status) throws IllegalArgumentException {
        Optional<Status> maybeStatusToUpdate = statusRepo.findById(id);
        if(maybeStatusToUpdate.isEmpty())
            throw new IllegalArgumentException("Invalid status");

        Status statusToUpdate = maybeStatusToUpdate.get();
        if(status.getName() != null && !status.getName().isBlank())
            statusToUpdate.setName(status.getName());

        statusRepo.save(statusToUpdate);
    }

    public void deleteStatus(int id) {
        statusRepo.deleteById(id);
    }
}
